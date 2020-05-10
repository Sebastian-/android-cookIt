package com.sebastianmurgu.cookit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.item_recipe.view.*
import kotlinx.android.synthetic.main.item_step.view.*

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // get the recipe from the intent, if null will throw NPE
        val recipe: Recipe = intent?.extras?.getParcelable(recipeExtra)!!

        recipe_name.text = recipe.name
        Glide.with(this).load(recipe.imageURL).into(recipe_image)

        // recipe steps recycler view
        recipe_steps.layoutManager = LinearLayoutManager(this)
        recipe_steps.adapter = RecipeStepsAdapter(recipe.steps, this)
    }

    companion object {
        const val recipeExtra = "RECIPE"

        fun newIntent(recipe: Recipe, context: Context): Intent {
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra(recipeExtra, recipe)
            return intent
        }
    }
}

// This adapter will implement the necessary logic to define and populate our table
private class RecipeStepsAdapter(val steps: List<String>, val context: Context): RecyclerView.Adapter<RecipeStepsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepsHolder {
        return RecipeStepsHolder(LayoutInflater.from(context).inflate(R.layout.item_step, parent, false))
    }

    override fun getItemCount(): Int {
        return steps.count()
    }

    override fun onBindViewHolder(holder: RecipeStepsHolder, position: Int) {
        val step = steps[position]

        holder.itemView.item_step_name.text = step
    }
}

// This is a class to host the reusable view for each table row
private class RecipeStepsHolder(view: View): RecyclerView.ViewHolder(view)


