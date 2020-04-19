package by.crispypigeon.weatherapp.mvp.main.presenter

import by.crispypigeon.weatherapp.mvp.main.view.IMainView

class MainPresenter {
    var view : IMainView? = null

    constructor(mainview :IMainView){
        view = mainview
    }
}