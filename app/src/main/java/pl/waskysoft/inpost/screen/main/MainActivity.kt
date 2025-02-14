@file:OptIn(ExperimentalMaterial3Api::class)

package pl.waskysoft.inpost.screen.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.waskysoft.inpost.R
import pl.waskysoft.inpost.screen.BaseActivity
import pl.waskysoft.inpost.ui.theme.AppTheme

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val label = getString(R.string.main_screen_title)

        setContent {
            MainActivityContent(label)
        }
    }

}

@Composable
private fun MainActivityContent(title: String) {
    val viewModel: MainViewModel = viewModel(factory = MainViewModel.Companion.Factory)
    val counter = viewModel.counter.collectAsStateWithLifecycle()

    AppTheme {
        MainScreen(
            title,
            counter = counter,
            onIncrementCounterClick = { viewModel.incrementCounter() }
        )
    }
}

@Composable
private fun MainScreen(
    title: String,
    counter: State<Int>,
    onIncrementCounterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { AppBar(title) }
    ) { innerPadding ->
        ScreenContent(
            counter = counter,
            onIncrementCounterClick = onIncrementCounterClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun AppBar(title: String, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    )
}

@Composable
private fun ScreenContent(
    counter: State<Int>, onIncrementCounterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Crossfade(targetState = counter.value) { targetValue ->
            Text(stringResource(R.string.counter_value, targetValue))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onIncrementCounterClick) { Text(stringResource(R.string.increment_counter)) }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            title = "Hello!",
            counter = remember { mutableIntStateOf(1) },
            onIncrementCounterClick = { }
        )
    }
}