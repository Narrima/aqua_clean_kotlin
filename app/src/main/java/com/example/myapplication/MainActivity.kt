package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o layout da atividade principal a partir do arquivo XML
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura a barra de ação (ActionBar) com a barra de ferramentas (Toolbar)
        setSupportActionBar(binding.appBarMain.toolbar)

        // Obtém referências para o DrawerLayout e NavigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Configura a navegação usando o NavController
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Define as configurações da AppBar, especificando os destinos de menu e o DrawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_favorites , R.id.nav_assessments, R.id.nav_login, R.id.menu_profile, R.id.nav_home,
                R.id.nav_createAccount
            ), drawerLayout
        )

        // Configura a ActionBar para trabalhar com a navegação
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Configura o NavigationView para trabalhar com a navegação
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Infla o menu na barra de ação, se estiver presente
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        // Configura a navegação de retorno na ActionBar
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
