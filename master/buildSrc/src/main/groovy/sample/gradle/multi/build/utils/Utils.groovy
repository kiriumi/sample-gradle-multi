package sample.gradle.multi.build.utils

/**
 * ユーティリティクラス
 *
 */
final class Utils {

    /**
     * 指定のディレクトリが存在しない場合、ディレクトリを作成する
     *
     * @param dir ディレクトリ
     */
    def static makeDirIfNotExists(File dir) {

         if(!dir.exists()) { dir.mkdirs() }
    }
}
