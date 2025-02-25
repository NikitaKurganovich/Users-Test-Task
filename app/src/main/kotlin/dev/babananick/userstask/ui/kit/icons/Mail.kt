package dev.babananick.userstask.ui.kit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Mail: ImageVector
    get() {
        if (_Mail != null) {
            return _Mail!!
        }
        _Mail = ImageVector.Builder(
            name = "Mail",
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
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(1f, 3.5f)
                lineToRelative(0.5f, -0.5f)
                horizontalLineToRelative(13f)
                lineToRelative(0.5f, 0.5f)
                verticalLineToRelative(9f)
                lineToRelative(-0.5f, 0.5f)
                horizontalLineToRelative(-13f)
                lineToRelative(-0.5f, -0.5f)
                verticalLineToRelative(-9f)
                close()
                moveToRelative(1f, 1.035f)
                verticalLineTo(12f)
                horizontalLineToRelative(12f)
                verticalLineTo(4.536f)
                lineTo(8.31f, 8.9f)
                horizontalLineTo(7.7f)
                lineTo(2f, 4.535f)
                close()
                moveTo(13.03f, 4f)
                horizontalLineTo(2.97f)
                lineTo(8f, 7.869f)
                lineTo(13.03f, 4f)
                close()
            }
        }.build()
        return _Mail!!
    }

private var _Mail: ImageVector? = null
