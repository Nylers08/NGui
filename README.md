# NGui

NGui — библиотека меню для плагинов Paper. Вы описываете кнопку (как она выглядит и что делает по клику) и меню (куда эти кнопки положить). Открытие инвентаря, клики и закрытие берёт на себя NGui.

Гайд рассчитан на автора плагина, который впервые подключает NGui. Рабочий образец в репозитории — пакет `io.neris.NGui.core.example`. Команда `/menu test` открывает это меню на сервере (право `ngui.test`, по умолчанию у операторов).

Нужны **Java 25** и **Paper 26.2** или новее.

## Как это устроено

У меню и кнопки разные роли.

**Меню** — инвентарь с заголовком. Его создаёт `MenuFactory`: метод `create` получает контекст (обычно UUID игрока) и возвращает готовое `Menu`. В слоты кладётся уже собранный предмет кнопки.

**Кнопка** — предмет в слоте. Её собирают из двух частей:

- **Renderer** рисует `ItemStack`: материал, количество, название.
- **Action** описывает реакцию на клик. При создании кнопки действие записывается в NBT предмета. Когда игрок кликает, NGui читает ключи с предмета и вызывает зарегистрированное действие.

Плагин NGui должен стоять на сервере. Слушатели кликов и сервис меню поднимаются в его `onEnable`. Из своего плагина к ним обращаются так:

```java
MenuServiceController ngui = StaticMenuServiceController.getServiceController();
```

Вызов имеет смысл после старта NGui. Для этого в `plugin.yml` своего плагина укажите зависимость `NGui` — тогда ваш `onEnable` выполнится позже.

## Подключение к своему плагину

### 1. Поставить NGui в локальный Maven

В репозитории библиотеки:

```bash
git clone https://github.com/Nylers08/NGui.git
cd NGui
mvn clean install
```

Команда кладёт артефакт `io.neris:ngui:0.0.6` в локальный репозиторий Maven. Туда же попадает jar плагина: `target/ngui-0.0.6.jar`.

### 2. Зависимость в `pom.xml`

NGui на сервере уже содержит эти классы, в свой jar их упаковывать не нужно. Область `provided` оставляет библиотеку только на время компиляции.

```xml
<dependency>
    <groupId>io.neris</groupId>
    <artifactId>ngui</artifactId>
    <version>0.0.6</version>
    <scope>provided</scope>
</dependency>
```

Рядом остаётся обычная зависимость `paper-api` с областью `provided`.

### 3. Зависимость в `plugin.yml`

```yaml
name: MyShop
version: 1.0.0
main: com.example.shop.MyShopPlugin
api-version: '26.2'
depend:
  - NGui
```

`depend` делает две вещи: Paper не включит ваш плагин без NGui и откроет вашему плагину классы NGui.

### 4. Jar на сервер

Скопируйте `ngui-0.0.6.jar` в `plugins/` вместе со своим плагином и перезапустите сервер.

## Меню из одной кнопки

Ниже — минимальный плагин: команда `/shop` открывает сундук, в центре лежит изумруд. Клик по изумруду отменяется, предмет из меню забрать нельзя.

Слоты нумеруются с нуля, слева направо, сверху вниз:

```text
 0  1  2  3  4  5  6  7  8
 9 10 11 12 13 14 15 16 17
18 19 20 21 22 23 24 25 26
```

### Кнопка

`StaticNbtButton` сам записывает действия в NBT. `SimpleBtnController` принимает материал, количество, название и список действий. `CancelPutAction` отменяет клик, чтобы игрок не забрал предмет.

```java
package com.example.shop;

import io.neris.NGui.core.gui.button.button.StaticNbtButton;
import io.neris.NGui.core.gui.button.controller.SimpleBtnController;
import io.neris.NGui.core.gui.button.controller.actions.CancelPutAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.UUID;

public class InfoButton extends StaticNbtButton {

    public InfoButton(UUID playerId) {
        SimpleBtnController controller = new SimpleBtnController(
                Material.EMERALD,
                1,
                Component.text("Информация"),
                new CancelPutAction()
        );
        super(controller, new ButtonRenderContext(playerId));
    }
}
```

`ButtonRenderContext` хранит UUID игрока, для которого рисуется предмет. Рендерер может использовать его, чтобы подставить ник. `SimpleRenderer` внутри `SimpleBtnController` UUID не читает: название задано заранее.

Свой вид кнопки делается через `ButtonRenderer`:

```java
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderer;
import io.neris.NGui.core.utils.itemStack.ItemStackUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class NickRenderer implements ButtonRenderer {

    @Override
    public ItemStack render(ButtonRenderContext context) {
        String name = Bukkit.getOfflinePlayer(context.getPlayerUUID()).getName();
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemStackUtils.rename(item, Component.text("Привет, " + name));
        return item;
    }
}
```

Такой рендерер передаётся в `ButtonController` вместе со списком действий. Список должен быть изменяемым (`new ArrayList<>()`), если действия будут добавляться после создания контроллера. `List.of(...)` для этого не подходит.

```java
ButtonRenderer renderer = new NickRenderer();
ButtonController controller = new ButtonController(renderer, new ArrayList<>());
controller.addAction(new CancelPutAction());
Button button = new StaticNbtButton(controller, new ButtonRenderContext(playerId));
```

### Меню

`MenuFactory<UUID>` собирает меню под конкретного игрока. `getMenuName()` — короткое имя латиницей: цифры, строчные буквы и `_`. Это имя попадает в ключ NBT, когда кнопка открывает другое меню.

```java
package com.example.shop;

import io.neris.NGui.core.gui.menu.BaseMenu;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.services.MenuFactory;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class ShopMenuFactory implements MenuFactory<UUID> {

    @Override
    public String getMenuName() {
        return "shop_main";
    }

    @Override
    public Menu create(UUID playerId) {
        Menu menu = new BaseMenu(27, Component.text("Магазин"));
        menu.setItem(new InfoButton(playerId).getItem(), 13);
        return menu;
    }
}
```

`setItem` кладёт один и тот же предмет в перечисленные слоты:

```java
menu.setItem(new InfoButton(playerId).getItem(), 0, 1, 2);
```

`new BaseMenu(27, title)` открывает сундук на 27 слотов. Другой размер собирается из готового инвентаря. Допустимые размеры сундука: 9, 18, 27, 36, 45, 54.

```java
Component title = Component.text("Большой магазин");
Inventory inventory = Bukkit.createInventory(null, 54, title);
Menu menu = new BaseMenu(inventory, title);
```

Уже открытое меню можно поменять на глазах у игрока:

```java
menu.changeName(Component.text("Новый заголовок"));
menu.changeSize(45);
menu.clear();
menu.reopen();
```

`changeName` и `changeSize` сами вызывают `reopen`.

### Открытие по команде

```java
package com.example.shop;

import io.neris.NGui.core.services.MenuServiceController;
import io.neris.NGui.core.services.StaticMenuServiceController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShopCommand implements CommandExecutor {

    private final ShopMenuFactory menus = new ShopMenuFactory();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Команда доступна только игроку");
            return true;
        }

        MenuServiceController ngui = StaticMenuServiceController.getServiceController();
        ngui.getPlayerMenuOpener().open(player, menus.create(player.getUniqueId()));
        return true;
    }
}
```

Регистрация в плагине:

```java
@Override
public void onEnable() {
    getCommand("shop").setExecutor(new ShopCommand());
}
```

И в `plugin.yml`:

```yaml
commands:
  shop:
    description: Открыть магазин
```

`PlayerMenuOpener.open` запоминает, какое меню у игрока открыто, и показывает инвентарь.

Закрыть меню из кода:

```java
MenuServiceController ngui = StaticMenuServiceController.getServiceController();

ngui.getPlayerMenuCloser().close(player.getUniqueId());
ngui.getPlayerMenuCloser().close(menu);   // всем, у кого открыт этот экземпляр
ngui.getPlayerMenuCloser().closeAll();
```

Текущее меню игрока:

```java
Menu opened = ngui.getOpenedMenuRegistry().getMenu(player.getUniqueId());
```

## Кнопка, которая открывает другое меню

Второе меню описывается фабрикой с контекстом клика. `OpenMenuFactoryButton` по клику вызывает `create` и открывает результат. Игрок, инвентарь и тип клика лежат в `ClickEventContext`.

```java
package com.example.shop;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.button.button.Button;
import io.neris.NGui.core.gui.menu.BaseMenu;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.services.MenuFactory;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class DetailMenuFactory implements MenuFactory<ClickEventContext> {

    @Override
    public String getMenuName() {
        return "shop_detail";
    }

    @Override
    public Menu create(ClickEventContext click) {
        Player player = (Player) click.whoClicked;
        Button info = new InfoButton(player.getUniqueId());

        Menu menu = new BaseMenu(27, Component.text("Подробности"));
        menu.setItem(info.getItem(), 13);
        return menu;
    }
}
```

Кнопка перехода. `CancelPutAction` по-прежнему нужен: переход отменяет клик, а `CancelPutAction` не даёт забрать стрелку.

```java
package com.example.shop;

import io.neris.NGui.core.gui.button.button.OpenMenuFactoryButton;
import io.neris.NGui.core.gui.button.controller.SimpleBtnController;
import io.neris.NGui.core.gui.button.controller.actions.CancelPutAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.menu.services.PlayerMenuOpener;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public class OpenDetailButton extends OpenMenuFactoryButton {

    public OpenDetailButton(ButtonRenderContext context, PlayerMenuOpener opener) {
        SimpleBtnController controller = new SimpleBtnController(
                Material.ARROW,
                1,
                Component.text("Дальше"),
                new CancelPutAction()
        );
        super(controller, context, opener, new DetailMenuFactory());
    }
}
```

Положите её в первое меню:

```java
@Override
public Menu create(UUID playerId) {
    MenuServiceController ngui = StaticMenuServiceController.getServiceController();

    Menu menu = new BaseMenu(27, Component.text("Магазин"));
    menu.setItem(new InfoButton(playerId).getItem(), 13);
    menu.setItem(
            new OpenDetailButton(
                    new ButtonRenderContext(playerId),
                    ngui.getPlayerMenuOpener()
            ).getItem(),
            26
    );
    return menu;
}
```

Так сделан пример в репозитории: `TestMenuFactory` кладёт `TestButton` и `TestOpenMenuFactoryButton`. Вторая кнопка открывает меню из `TestMenuFactoryWithClickContext`.

## Кнопка с сообщением

`MsgPlayerAction` уже зарегистрирован внутри NGui. Текст берётся из NBT предмета, поэтому в кнопку передаётся свой экземпляр с нужной фразой. В тексте сообщения не ставьте запятую: по ней NGui отделяет текст от флага «действие включено».

```java
NBTTagger tagger = StaticMenuServiceController.getServiceController().getNbtTagger();

SimpleBtnController controller = new SimpleBtnController(
        Material.PAPER,
        1,
        Component.text("Подсказка"),
        new CancelPutAction(),
        new MsgPlayerAction(tagger, "Это подсказка")
);
Button button = new StaticNbtButton(controller, new ButtonRenderContext(playerId));
```

По клику игрок получит сообщение `Это подсказка`.

## Своё действие

Новое поведение — класс `ButtonAction`. У него три метода:

- `key()` — ключ в NBT. Латиница в нижнем регистре, цифры и символы `_ . -`.
- `value()` — строка, которую NGui запишет на предмет. Для простого флага достаточно `"true"`.
- `execute(ClickEventContext)` — код клика.

Клик вызывает действие из общего реестра, а не тот объект, который лежал в кнопке. Поэтому действие регистрируют один раз при старте плагина. На предмет кнопка только пишет тот же ключ.

```java
package com.example.shop;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveDiamondAction implements ButtonAction {

    @Override
    public String key() {
        return "give_diamond";
    }

    @Override
    public String value() {
        return "true";
    }

    @Override
    public void execute(ClickEventContext click) {
        if (click.whoClicked instanceof Player player) {
            player.getInventory().addItem(new ItemStack(Material.DIAMOND));
        }
    }
}
```

```java
@Override
public void onEnable() {
    StaticMenuServiceController.getServiceController()
            .getNbtActionRegistry()
            .register(new GiveDiamondAction());

    getCommand("shop").setExecutor(new ShopCommand());
}
```

Кнопка с этим действием:

```java
SimpleBtnController controller = new SimpleBtnController(
        Material.DIAMOND,
        1,
        Component.text("Забрать алмаз"),
        new CancelPutAction(),
        new GiveDiamondAction()
);
```

Второй `GiveDiamondAction` в кнопку нужен только как носитель `key()` и `value()`: по ним на предмет пишется NBT. Выполнится экземпляр из реестра.

Если разным кнопкам нужны разные данные при одном и том же ключе, кладите данные в `value()` и читайте их в `execute` с предмета. Так устроен `MsgPlayerAction`: реестр один, текст у каждой кнопки свой.

В `ClickEventContext` доступны:

| Поле | Что внутри |
| --- | --- |
| `whoClicked` | кто кликнул |
| `inventory` | открытый инвентарь |
| `clickType` | левый, правый, шифт и остальные типы клика |
| `items` | предметы, к которым относится событие |
| `event` | исходное событие Bukkit |

## Готовые действия

| Класс | Поведение |
| --- | --- |
| `CancelPutAction` | Отменяет событие. Стоит добавлять почти на каждую кнопку меню, иначе предмет можно забрать или передвинуть. Уже зарегистрирован. |
| `MsgPlayerAction` | Пишет игроку текст из NBT. Уже зарегистрирован. |
| `ToMenuFactoryAction` | Открывает меню из `MenuFactory<ClickEventContext>`. Регистрируется сам в конструкторе. Удобная обёртка — `OpenMenuFactoryButton`. |
| `ToMenuAction` | Открывает уже собранный экземпляр `Menu`. Регистрируется сам. Обёртка — наследник `OpenMenuButton`. |

Для перехода между экранами берите `OpenMenuFactoryButton`. Ключ действия собирается из `getMenuName()`, и вы сами задаёте безопасное имя.

## Схема классов

```text
MenuFactory.create(...)
        └── Menu (BaseMenu)
                └── слот ← Button.getItem()
                        └── StaticNbtButton
                                ├── ButtonRenderer  → ItemStack
                                └── ButtonAction    → NBT на предмете

клик игрока
        └── NGui слушает InventoryClickEvent
                └── по ключам NBT находит ButtonAction в реестре
                        └── action.execute(ClickEventContext)
```

Сервисы, которые нужны снаружи:

| Метод | Зачем |
| --- | --- |
| `getPlayerMenuOpener()` | открыть меню игроку |
| `getPlayerMenuCloser()` | закрыть меню игроку, всем зрителям одного меню или всем сразу |
| `getOpenedMenuRegistry()` | узнать, какое меню сейчас открыто у игрока |
| `getNbtActionRegistry()` | зарегистрировать своё `ButtonAction` |
| `getNbtTagger()` | читать и писать NBT; его просит `MsgPlayerAction` |

## Команды самого NGui

Они нужны, чтобы посмотреть встроенный пример и закрыть зависшее меню. Все права по умолчанию только у операторов.

| Команда | Право | Что делает |
| --- | --- | --- |
| `/menu` | `ngui.help` | список подкоманд |
| `/menu test` | `ngui.test` | меню из `TestMenuFactory` |
| `/menu closefor <ник>` | `ngui.closefor` | закрыть меню игроку |
| `/menu closeall` | `ngui.closeall` | закрыть все меню NGui |

## Частые ошибки

**`StaticMenuServiceController.getServiceController()` возвращает `null`.** NGui ещё не включился. Проверьте `depend: [NGui]` и что jar лежит в `plugins/`.

**Клик ничего не делает.** Действие есть на кнопке, но его нет в `NbtActionRegistry`. `CancelPutAction` и `MsgPlayerAction` регистрируются сами. Свой класс нужно зарегистрировать в `onEnable`. `ToMenuAction` и `ToMenuFactoryAction` регистрируются в своём конструкторе.

**Предмет утаскивается из меню.** На кнопку не добавлен `CancelPutAction`.

**Падение при создании кнопки перехода.** `getMenuName()` содержит пробел, кириллицу или другой символ вне `[a-z0-9/._-]`. Имя становится частью ключа NBT. Регистр NGui приводит к нижнему сам, пробелы и нелатинские буквы остаются проблемой.

**Две кнопки открывают одно и то же меню, хотя фабрики разные.** У `MenuFactory` совпадает `getMenuName()`. Ключ в реестре один, и в нём остаётся фабрика последней созданной кнопки. Имена экранов должны отличаться.
