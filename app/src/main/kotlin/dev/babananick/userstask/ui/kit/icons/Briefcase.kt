package dev.babananick.userstask.ui.kit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Briefcase: ImageVector
    get() {
        if (_Briefcase != null) {
            return _Briefcase!!
        }
        _Briefcase = ImageVector.Builder(
            name = "Briefcase",
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
                moveTo(14.5f, 4f)
                horizontalLineTo(11f)
                verticalLineTo(2.5f)
                lineToRelative(-0.5f, -0.5f)
                horizontalLineToRelative(-5f)
                lineToRelative(-0.5f, 0.5f)
                verticalLineTo(4f)
                horizontalLineTo(1.5f)
                lineToRelative(-0.5f, 0.5f)
                verticalLineToRelative(8f)
                lineToRelative(0.5f, 0.5f)
                horizontalLineToRelative(13f)
                lineToRelative(0.5f, -0.5f)
                verticalLineToRelative(-8f)
                lineToRelative(-0.5f, -0.5f)
                close()
                moveTo(6f, 3f)
                horizontalLineToRelative(4f)
                verticalLineToRelative(1f)
                horizontalLineTo(6f)
                verticalLineTo(3f)
                close()
                moveToRelative(8f, 2f)
                verticalLineToRelative(0.76f)
                lineTo(10f, 8f)
                verticalLineToRelative(-0.5f)
                lineTo(9.51f, 7f)
                horizontalLineToRelative(-3f)
                lineTo(6f, 7.5f)
                verticalLineTo(8f)
                lineTo(2f, 5.71f)
                verticalLineTo(5f)
                horizontalLineToRelative(12f)
                close()
                moveTo(9f, 8f)
                verticalLineToRelative(1f)
                horizontalLineTo(7f)
                verticalLineTo(8f)
                horizontalLineToRelative(2f)
                close()
                moveToRelative(-7f, 4f)
                verticalLineTo(6.86f)
                lineToRelative(4f, 2.29f)
                verticalLineToRelative(0.35f)
                lineToRelative(0.5f, 0.5f)
                horizontalLineToRelative(3f)
                lineToRelative(0.5f, -0.5f)
                verticalLineToRelative(-0.31f)
                lineToRelative(4f, -2.28f)
                verticalLineTo(12f)
                horizontalLineTo(2f)
                close()
            }
        }.build()
        return _Briefcase!!
    }

private var _Briefcase: ImageVector? = null
