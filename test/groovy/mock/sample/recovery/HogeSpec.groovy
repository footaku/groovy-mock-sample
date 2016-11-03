package groovy.mock.sample.recovery

import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * @author panage
 */
class HogeSpec extends Specification {
    def "groovyで処理をオーバーライドする"() {
        given:
        // getHogeメソッドの戻り値をfugaに変える
        Hoge.metaClass.getHoge = {
            "fuga"
        }

        when:
        // getHogeを実行する
        def hoge = new Hoge().getHoge()

        then:
        // getHogeの値がfugaに変わっているはず
        hoge == "fuga"
    }

    def "groovyでオーバーライドされた処理を実行する"() {
        given:
        // ここではHogeクラスを書き換えない

        when:
        // getHogeを実行する
        def hoge = new Hoge().getHoge()

        then:
        // getHogeの値はhogeのままと思いきやfugaに変わったまま
        hoge == "fuga"
    }

    @ConfineMetaClassChanges(Hoge)
    def "再度オーバーライドするけどConfineMetaClassChangesアノテーションがついてる"() {
        given:
        // getHogeメソッドの戻り値をpiyoに変える
        Hoge.metaClass.getHoge = {
            "piyo"
        }

        when:
        // getHogeを実行する
        def hoge = new Hoge().getHoge()

        then:
        // getHogeの値がpiyoに変わっているはず
        hoge == "piyo"
    }

    def "ConfineMetaClassChangesアノテーション付きでオーバーライドされた処理を実行する"() {
        given:
        // ここではHogeクラスを書き換えない

        when:
        // getHogeを実行する
        def hoge = new Hoge().getHoge()

        then:
        // さっきはメソッド間で処理が変わってなかったので
        // getHogeの値はpiyoのままと思いきや
        // piyoへの変更は破棄されてfugaに戻っている
        hoge == "fuga"
    }

    class Hoge {
        def String getHoge() {
            "hoge"
        }
    }
}
