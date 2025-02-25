package dev.babananick.userstask.ui.kit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val User: ImageVector
    get() {
        if (_User != null) {
            return _User!!
        }
        _User = ImageVector.Builder(
            name = "User",
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
                moveTo(16f, 7.992f)
                curveTo(16f, 3.58f, 12.416f, 0f, 8f, 0f)
                reflectiveCurveTo(0f, 3.58f, 0f, 7.992f)
                curveToRelative(0f, 2.43f, 1.104f, 4.62f, 2.832f, 6.09f)
                curveToRelative(0.016f, 0.016f, 0.032f, 0.016f, 0.032f, 0.032f)
                curveToRelative(0.144f, 0.112f, 0.288f, 0.224f, 0.448f, 0.336f)
                curveToRelative(0.08f, 0.048f, 0.144f, 0.111f, 0.224f, 0.175f)
                arcTo(7.98f, 7.98f, 0f, isMoreThanHalf = false, isPositiveArc = false, 8.016f, 16f)
                arcToRelative(7.98f, 7.98f, 0f, isMoreThanHalf = false, isPositiveArc = false, 4.48f, -1.375f)
                curveToRelative(0.08f, -0.048f, 0.144f, -0.111f, 0.224f, -0.16f)
                curveToRelative(0.144f, -0.111f, 0.304f, -0.223f, 0.448f, -0.335f)
                curveToRelative(0.016f, -0.016f, 0.032f, -0.016f, 0.032f, -0.032f)
                curveToRelative(1.696f, -1.487f, 2.8f, -3.676f, 2.8f, -6.106f)
                close()
                moveToRelative(-8f, 7.001f)
                curveToRelative(-1.504f, 0f, -2.88f, -0.48f, -4.016f, -1.279f)
                curveToRelative(0.016f, -0.128f, 0.048f, -0.255f, 0.08f, -0.383f)
                arcToRelative(4.17f, 4.17f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.416f, -0.991f)
                curveToRelative(0.176f, -0.304f, 0.384f, -0.576f, 0.64f, -0.816f)
                curveToRelative(0.24f, -0.24f, 0.528f, -0.463f, 0.816f, -0.639f)
                curveToRelative(0.304f, -0.176f, 0.624f, -0.304f, 0.976f, -0.4f)
                arcTo(4.15f, 4.15f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8f, 10.342f)
                arcToRelative(4.185f, 4.185f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2.928f, 1.166f)
                curveToRelative(0.368f, 0.368f, 0.656f, 0.8f, 0.864f, 1.295f)
                curveToRelative(0.112f, 0.288f, 0.192f, 0.592f, 0.24f, 0.911f)
                arcTo(7.03f, 7.03f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8f, 14.993f)
                close()
                moveToRelative(-2.448f, -7.4f)
                arcToRelative(2.49f, 2.49f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.208f, -1.024f)
                curveToRelative(0f, -0.351f, 0.064f, -0.703f, 0.208f, -1.023f)
                curveToRelative(0.144f, -0.32f, 0.336f, -0.607f, 0.576f, -0.847f)
                curveToRelative(0.24f, -0.24f, 0.528f, -0.431f, 0.848f, -0.575f)
                curveToRelative(0.32f, -0.144f, 0.672f, -0.208f, 1.024f, -0.208f)
                curveToRelative(0.368f, 0f, 0.704f, 0.064f, 1.024f, 0.208f)
                curveToRelative(0.32f, 0.144f, 0.608f, 0.336f, 0.848f, 0.575f)
                curveToRelative(0.24f, 0.24f, 0.432f, 0.528f, 0.576f, 0.847f)
                curveToRelative(0.144f, 0.32f, 0.208f, 0.672f, 0.208f, 1.023f)
                curveToRelative(0f, 0.368f, -0.064f, 0.704f, -0.208f, 1.023f)
                arcToRelative(2.84f, 2.84f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.576f, 0.848f)
                arcToRelative(2.84f, 2.84f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.848f, 0.575f)
                arcToRelative(2.715f, 2.715f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2.064f, 0f)
                arcToRelative(2.84f, 2.84f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.848f, -0.575f)
                arcToRelative(2.526f, 2.526f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.56f, -0.848f)
                close()
                moveToRelative(7.424f, 5.306f)
                curveToRelative(0f, -0.032f, -0.016f, -0.048f, -0.016f, -0.08f)
                arcToRelative(5.22f, 5.22f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.688f, -1.406f)
                arcToRelative(4.883f, 4.883f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.088f, -1.135f)
                arcToRelative(5.207f, 5.207f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.04f, -0.608f)
                arcToRelative(2.82f, 2.82f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.464f, -0.383f)
                arcToRelative(4.2f, 4.2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.624f, -0.784f)
                arcToRelative(3.624f, 3.624f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.528f, -1.934f)
                arcToRelative(3.71f, 3.71f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.288f, -1.47f)
                arcToRelative(3.799f, 3.799f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.816f, -1.199f)
                arcToRelative(3.845f, 3.845f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.2f, -0.8f)
                arcToRelative(3.72f, 3.72f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.472f, -0.287f)
                arcToRelative(3.72f, 3.72f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.472f, 0.288f)
                arcToRelative(3.631f, 3.631f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.2f, 0.815f)
                arcToRelative(3.84f, 3.84f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.8f, 1.199f)
                arcToRelative(3.71f, 3.71f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.288f, 1.47f)
                curveToRelative(0f, 0.352f, 0.048f, 0.688f, 0.144f, 1.007f)
                curveToRelative(0.096f, 0.336f, 0.224f, 0.64f, 0.4f, 0.927f)
                curveToRelative(0.16f, 0.288f, 0.384f, 0.544f, 0.624f, 0.784f)
                curveToRelative(0.144f, 0.144f, 0.304f, 0.271f, 0.48f, 0.383f)
                arcToRelative(5.12f, 5.12f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.04f, 0.624f)
                curveToRelative(-0.416f, 0.32f, -0.784f, 0.703f, -1.088f, 1.119f)
                arcToRelative(4.999f, 4.999f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.688f, 1.406f)
                curveToRelative(-0.016f, 0.032f, -0.016f, 0.064f, -0.016f, 0.08f)
                curveTo(1.776f, 11.636f, 0.992f, 9.91f, 0.992f, 7.992f)
                curveTo(0.992f, 4.14f, 4.144f, 0.991f, 8f, 0.991f)
                reflectiveCurveToRelative(7.008f, 3.149f, 7.008f, 7.001f)
                arcToRelative(6.96f, 6.96f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2.032f, 4.907f)
                close()
            }
        }.build()
        return _User!!
    }

private var _User: ImageVector? = null
