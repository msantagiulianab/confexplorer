@file:JsModule("react-share")
@file:JsNonModule

import react.ComponentClass
import react.Props

@JsName("EmailIcon")
external val EmailIcon: ComponentClass<IconProps>

@JsName("EmailShareButton")
external val EmailShareButton: ComponentClass<ShareButtonProps>

@JsName("TelegramIcon")
external val TelegramIcon: ComponentClass<IconProps>

@JsName("TelegramShareButton")
external val TelegramShareButton: ComponentClass<ShareButtonProps>

@JsName("TwitterIcon")
external val TwitterIcon: ComponentClass<IconProps>

@JsName("TwitterShareButton")
external val TwitterShareButton: ComponentClass<ShareButtonProps>

external interface ShareButtonProps : Props {
    var url: String
}

external interface IconProps : Props {
    var size: Int
    var round: Boolean
}