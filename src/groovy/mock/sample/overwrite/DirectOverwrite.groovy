package groovy.mock.sample.overwrite

/**
 * @author panage
 */
class DirectOverwrite {
    static void main(String[] args) {
        def target = new Target()
        def dontTouch = new Target()

        // まずは普通に"not overwritten"を出力
        println target.get()

        // インスタンスに対して処理を上書き
        target.metaClass.get = {
            "overwrote!"
        }

        // 上書き確認 出力が"overwrote!"に変わる
        println target.get()

        // 別のインスタンスは変わらない
        println dontTouch.get()

        // クラスに対して処理を上書き
        Target.metaClass.get = {
            "overwrote!"
        }

        // クラスを書き換えたあとにインスタンスを生成すると変わる
        dontTouch = new Target()
        println dontTouch.get()

        // プロパティも足せる
        Target.metaClass.message = "add property"
        Target.metaClass.get = {
            message
        }
        def addProperty = new Target()
        println addProperty.get()

        // あとで足したプロパティも書き換えられる
        Target.metaClass.set = { String s ->
            message = s
        }
        def setProperty = new Target()
        setProperty.set("set property!")
        println setProperty.get()
    }
}
