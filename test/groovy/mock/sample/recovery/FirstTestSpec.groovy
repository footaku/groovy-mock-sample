package groovy.mock.sample.recovery

import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * @author panage
 */
@ConfineMetaClassChanges(Fuga)
class FirstTestSpec extends Specification {

    def "groovyで処理をオーバーライドする"() {
        given:
        // getFugaメソッドの戻り値をfugaに変える
        Fuga.metaClass.getFuga = {
            "piyo"
        }

        when:
        // getFugaを実行する
        def fuga = new Fuga().getFuga()

        then:
        // getFugaの値がfugaに変わっているはず
        fuga == "piyo"
    }

    def "groovyでオーバーライドされた処理を実行する"() {
        given:
        // ここではFugaクラスを書き換えない

        when:
        // getFugaを実行する
        def fuga = new Fuga().getFuga()

        then:
        // getFugaの値はpiyoのまま
        fuga == "piyo"
    }
}
