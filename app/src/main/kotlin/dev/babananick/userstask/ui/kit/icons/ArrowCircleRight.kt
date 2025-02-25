package dev.babananick.userstask.ui.kit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val ArrowCircleRight: ImageVector
    get() {
        if (_ArrowCircleRight != null) {
            return _ArrowCircleRight!!
        }
        _ArrowCircleRight = ImageVector.Builder(
            name = "ArrowCircleRight",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(8.08074f, 5.36891f)
                lineTo(10.2202f, 7.50833f)
                lineTo(4.46802f, 7.50833f)
                lineTo(4.46802f, 8.50833f)
                lineTo(10.1473f, 8.50833f)
                lineTo(8.08073f, 10.5749f)
                lineTo(8.78784f, 11.282f)
                lineTo(11.7444f, 8.32545f)
                lineTo(11.7444f, 7.61835f)
                lineTo(8.78784f, 4.6618f)
                lineTo(8.08074f, 5.36891f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(8f, 14f)
                curveTo(4.6863f, 14f, 2f, 11.3137f, 2f, 8f)
                curveTo(2f, 4.6863f, 4.6863f, 2f, 8f, 2f)
                curveTo(11.3137f, 2f, 14f, 4.6863f, 14f, 8f)
                curveTo(14f, 11.3137f, 11.3137f, 14f, 8f, 14f)
                close()
                moveTo(8f, 13f)
                curveTo(10.7614f, 13f, 13f, 10.7614f, 13f, 8f)
                curveTo(13f, 5.2386f, 10.7614f, 3f, 8f, 3f)
                curveTo(5.2386f, 3f, 3f, 5.2386f, 3f, 8f)
                curveTo(3f, 10.7614f, 5.2386f, 13f, 8f, 13f)
                close()
            }
        }.build()
        return _ArrowCircleRight!!
    }

private var _ArrowCircleRight: ImageVector? = null
