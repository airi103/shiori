package com.shirakawa.shiori

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun MusicScreen(channelId: Int) {
    val viewModel = MainViewModel()
    val posts by viewModel.posts.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Telegram Music") },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 10.dp
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
               .padding(horizontal = 16.dp, vertical = 8.dp)
               .fillMaxWidth()
               .shadow(elevation = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(posts) { post ->
                PostItem(post = post)
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
           .fillMaxWidth()
           .height(120.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = post.audio?.file_id,
                contentDescription = null,
                modifier = Modifier
                   .size(80.dp)
                   .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                   .fillMaxWidth()
                   .padding(end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = post.text,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Audio: ${post.audio?.file_id}",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }
        }
    }
}

data class Post(
    val id: Int,
    val text: String,
    val audio: Audio?
)

data class Audio(
    val file_id: String,
    val file_size: Int
)

class MainViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    fun loadPosts(channelId: Int) {
        // Load posts from Telegram API
        val posts = listOf(
            Post(1, "Post 1", Audio("file_id_1", 1024)),
            Post(2, "Post 2", Audio("file_id_2", 2048)),
            Post(3, "Post 3", Audio("file_id_3", 4096))
        )
        _posts.value = posts
    }
}

@Preview
@Composable
fun MusicScreenPreview() {
    MusicScreen(channelId = 123)
}
