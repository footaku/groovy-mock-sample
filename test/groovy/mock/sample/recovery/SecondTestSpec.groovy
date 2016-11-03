package groovy.mock.sample.recovery

import spock.lang.Specification

/**
 * @author panage
 */
class SecondTestSpec extends Specification {

    def "再度オーバーライドされた処理を実行する"() {
        given:
        // ここではFugaクラスを書き換えない

        when:
        // getFugaを実行する
        def fuga = new Fuga().getFuga()

        then:
        // ここではpiyoへの変更は破棄されてfugaに戻っている
        fuga == "fuga"
    }
}
