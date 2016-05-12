package gl8080.lifegame.logic.definition

/**
 * ゲーム定義の永続化を行うリポジトリ。
 */
interface GameDefinitionRepository {

    /**
     * ゲーム定義を登録する。
     * @param gameDefinition 登録するゲーム定義
     */
    fun register(gameDefinition: GameDefinition)

    /**
     * 指定した ID のゲーム定義を検索する。
     * @param id ID
     * @return ゲーム定義
     * @throws NotFoundEntityException ID に紐づくゲーム定義が存在しない場合
     */
    fun search(id: Long): GameDefinition

    /**
     * 指定した ID のゲーム定義を検索し、排他ロックする。
     * @param id ID
     * @return ゲーム定義
     * @throws NotFoundEntityException ID に紐づくゲーム定義が存在しない場合
     */
    fun searchWithLock(id: Long): GameDefinition

    /**
     * 指定したゲーム定義を削除する。
     * @param gameDefinition 削除するゲーム定義
     */
    fun remove(gameDefinition: GameDefinition)
}

