package gl8080.lifegame.logic.exception

class NotFoundEntityException(id: Long): RuntimeException("検索対象が存在しません id=$id")