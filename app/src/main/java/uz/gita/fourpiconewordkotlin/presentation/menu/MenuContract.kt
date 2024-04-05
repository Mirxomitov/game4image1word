package uz.gita.fourpiconewordkotlin.presentation.menu

interface MenuContract {
    interface View {
        fun showImages(images: IntArray)
        fun updateLevel(levelText: String)
        fun updateCoins(coinsText: String)
    }

    interface Model {
        fun clearResult()
        fun getImages(): IntArray
        fun getLevel() : Int
        fun getCoins() : Int
    }

    interface Presenter {
        fun clearResult()
        fun setImages()
        fun setCoins()
        fun setLevel()
    }
}