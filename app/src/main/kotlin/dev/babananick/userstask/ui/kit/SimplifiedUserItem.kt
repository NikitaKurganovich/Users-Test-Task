package dev.babananick.userstask.ui.kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.babananick.userstask.R
import dev.babananick.userstask.model.SimplifiedUser
import dev.babananick.userstask.ui.kit.icons.ArrowCircleRight
import dev.babananick.userstask.ui.kit.icons.Mail
import dev.babananick.userstask.ui.kit.icons.User

@Composable
fun SimplifiedUserItem(
    modifier: Modifier = Modifier,
    user: SimplifiedUser,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clip(MaterialTheme.shapes.small),
    ) {
        ListItem(
            tonalElevation = 4.dp,
            shadowElevation = 4.dp,
            overlineContent = {
                Text(stringResource(R.string.user_id_template, user.id))
            },
            leadingContent = {
                Image(
                    modifier = Modifier.size(48.dp),
                    imageVector = User,
                    contentDescription = stringResource(R.string.acsb_user_leading_icon_description),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
            },
            headlineContent = {
                Text(text = user.name)
            },
            trailingContent = {
                IconButton(
                    modifier = Modifier.clip(MaterialTheme.shapes.extraLarge),
                    onClick = onClick
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = ArrowCircleRight,
                        contentDescription = stringResource(R.string.acsb_user_trailing_icon_description),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            supportingContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Mail,
                        contentDescription = stringResource(R.string.acsb_user_supporting_icon_description),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Text(user.email)
                }
            }
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SimplifiedUserPreview() {
    MaterialTheme {
        Column {
            SimplifiedUserItem(
                modifier = Modifier.padding(4.dp),
                user = SimplifiedUser(
                    id = 0,
                    name = "John Doe",
                    username = "SomeCoolUser",
                    email = "example@mail.com"
                ),
                onClick = {}
            )
            SimplifiedUserItem(
                modifier = Modifier.padding(4.dp),
                user = SimplifiedUser(
                    id = 4430,
                    name = "John Doe John Doe John Doe",
                    username = "SomeCoolUser",
                    email = "example@mail.com"
                ),
                onClick = {}
            )
        }

    }
}