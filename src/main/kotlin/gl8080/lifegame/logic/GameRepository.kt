package gl8080.lifegame.logic

/**
 * ゲームの永続化を行うリポジトリ。
 */
interface GameRepository {

    /**
     * ゲームを登録する。
     * @param game 登録するゲーム
     */
    fun register(game: Game)

    /**
     * 指定した ID のゲームを検索する。
     * @param id ID
     * @return 検索結果
     * @throws NotFoundEntityException 指定した ID に紐づくゲームが存在しない場合
     */
    fun search(id: Long): Game

    /**
     * 指定した ID のゲームを検索し、排他ロックする。
     * @param id ID
     * @return 検索結果
     * @throws NotFoundEntityException 指定した ID に紐づくゲームが存在しない場合
     */
    fun searchWithLock(id: Long): Game

    /**
     * 指定したゲームを削除する。
     * @param game 削除するゲーム。
     */
    fun remove(game: Game)
}