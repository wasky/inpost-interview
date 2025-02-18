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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import pl.waskysoft.inpost.R
import pl.waskysoft.inpost.screen.BaseActivity
import pl.waskysoft.inpost.screen.notes.NotesScreenEntry
import pl.waskysoft.inpost.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComposable()
        }
    }

}

@Serializable
private object MainScreenDestination

@Serializable
private object NotesScreenDestination

@Composable
private fun NavigationComposable() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreenDestination) {
        composable<MainScreenDestination> {
            MainActivityContent(
                onNavigateToNotesScreen = { navController.navigate(route = NotesScreenDestination) }
            )
        }
        composable<NotesScreenDestination> { NotesScreenEntry() }
    }
}

@Composable
private fun MainActivityContent(onNavigateToNotesScreen: () -> Unit) {
    val viewModel: MainViewModel = hiltViewModel()
    val counter = viewModel.counterValue.collectAsStateWithLifecycle()

    AppTheme {
        MainScreen(
            counter = counter,
            onIncrementCounterClick = { viewModel.incrementCounter() },
            onNavigateToNotesScreen = onNavigateToNotesScreen
        )
    }
}

@Composable
private fun MainScreen(
    counter: State<Int>,
    onIncrementCounterClick: () -> Unit,
    onNavigateToNotesScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { AppBar(stringResource(R.string.main_screen_title)) }
    ) { innerPadding ->
        ScreenContent(
            counter = counter,
            onIncrementCounterClick = onIncrementCounterClick,
            onNavigateToNotesScreen = onNavigateToNotesScreen,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun ScreenContent(
    counter: State<Int>, onIncrementCounterClick: () -> Unit,
    onNavigateToNotesScreen: () -> Unit,
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
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToNotesScreen) { Text("Notes") }
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            counter = remember { mutableIntStateOf(1) },
            onIncrementCounterClick = {},
            onNavigateToNotesScreen = {}
        )
    }
}