package kr.leesunr.news.core.base.command

data class  NavigationCommand(
    val destination: Destination,
    val args: Map<Key, Any> = emptyMap()
)

enum class Destination {
    WEB_VIEW
}

enum class Key {
    URL,
    TITLE
}