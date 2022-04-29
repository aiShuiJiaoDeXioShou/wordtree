package com.wordtree.view

import com.wordtree.Coder
import com.wordtree.controller.addFileThrift
import com.wordtree.controller.saveFile
import com.wordtree.tools.AppConfig
import com.wordtree.wt_kt_note_book.*
import com.wordtree.wt_kt_note_book.module_view_entity.YtIcon
import com.wordtree.tools.ReadUtil
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.control.TreeItem
import javafx.stage.DirectoryChooser

private val 保存 = MenuItem(AppConfig.FileMenu_保存)
private val 打开文件 = MenuItem(AppConfig.FileMenu_打开)
private val 打开文件夹 = MenuItem(AppConfig.FileMenu_打开文件夹)
private val 新建项目 = MenuItem(AppConfig.FileMenu_新建项目)
private val 新建窗口 = MenuItem(AppConfig.FileMenu_窗口)

private var 设置菜单栏: Menu? = null

fun 菜单栏() {
    //从配置文件中获取菜单项数据
    val menuBarData = ReadUtil.propertiesItem("menu_bar") as Map<String,Any>
    //这里是上界面menu的内容
    val menu = Menu("文件").apply { styleClass.add("zkh_MenuItem") }
    menu.items.addAll(保存, 打开文件, 打开文件夹, 新建项目, 新建窗口)

    //获取设置面板的数据
    val settingData = menuBarData.get("setting") as Map<String,Any>
    设置菜单栏 = Menu(settingData.get("item_name") as String).apply { styleClass.add("zkh_MenuItem"); }
    val settingMenuChildren = settingData.get("children") as ArrayList<String>
    if (settingMenuChildren.size != 0 ){
        for (settingChild in settingMenuChildren) {
            设置菜单栏!!.items.add(MenuItem(settingChild))
        }
    }

    topBar.apply {
        padding = Insets(2.0)
        styleClass.add("zkh_MenuBar")
        menus.addAll(menu, 设置菜单栏)//将menu放到菜单栏当中
    }
    root.top = topBar
    菜单栏操作()
}

private fun 菜单栏操作() {
    保存.onAction = EventHandler {
        saveFile()
    }
    打开文件.onAction = EventHandler {

    }
    打开文件夹.onAction = EventHandler {
        val chooser = DirectoryChooser()
        chooser.title = "请选择文件夹"
        val fileChoose = chooser.showDialog(topBar.scene.window)
        if (fileChoose != null) {
            val file = fileChoose
            val treeItem = TreeItem<Label>()
            treeItem.value = Label(file.name).apply { graphic = YtIcon(ReadUtil.ImageUrl2("FileSet")) }
            addFileThrift(file.listFiles(), treeItem)
            fileTreeView.root = treeItem
            ReadUtil.addProperty("fileChoose",fileChoose.toString());
        }
    }
    新建项目.onAction = EventHandler {
        //跳出弹窗输入项目名称或者其他的东西
        val fxmlLoader = FXMLLoader(None().javaClass.getResource("新建项目根.fxml"))
        新建项目弹窗(fxmlLoader.load())
    }
    新建窗口.onAction = EventHandler {

    }
    for (item in 设置菜单栏!!.items) {
        when(item.text){
            "打开设置面板"-> item.onAction = EventHandler {
                设置面板()
            }
        }
    }
}

private data class MenuItemReader(val item_name:String,val children:ArrayList<String>){

}
