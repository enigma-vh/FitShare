package com.mongodb.tasktracker

import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mongodb.tasktracker.model.Recipe
import io.realm.Realm
import io.realm.RealmList

class RecipeActivity: AppCompatActivity() {
    private lateinit var recipeRealm: Realm
    private var user: io.realm.mongodb.User? = null
    private lateinit var recyclerView: RecyclerView
    //add recipe adapter

    //Recipe declarations
    private lateinit var recipeName: EditText
    private lateinit var description: EditText
    private lateinit var ingredients: EditText
    private lateinit var steps: EditText
    private lateinit var submitButton: Button

    override fun onStart(){
        super.onStart()
        setContentView(R.layout.activity_recipe)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        recipeName = findViewById(R.id.recipe_name)
        description = findViewById(R.id.recipe_description)
        ingredients = findViewById(R.id.recipe_ingredients)
        steps = findViewById(R.id.recipe_steps)

        val recipe1 = Recipe("test name", "test description",
            " ingredient 1 ingredient 2", "step 1 step 2")
        //write recipe to realm
        recipeRealm.executeTransactionAsync { realm -> realm.insert(recipe1) }

        submitButton = findViewById(R.id.recipe_button)
        submitButton.setOnClickListener {
            val recipe = Recipe(recipeName.toString(), description.toString(),
                ingredients.toString(), steps.toString())
            //write recipe to realm
            recipeRealm.executeTransactionAsync { realm -> realm.insert(recipe) }
        }
    }

    override fun onStop(){
        super.onStop()
    }

    override fun onDestroy(){
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu:Menu): Boolean{
        return true
    }

    private fun getRecipes(realm: Realm): RealmList<Recipe>{
        return RealmList<Recipe>()
    }

    private fun setUpRecyclerView(recipeList: RealmList<Recipe>){
        
    }
}