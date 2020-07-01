package com.example.two2three

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fun onAttachFragment(fragment: Fragment)
        {
            if(fragment is HeadlinesFragment)
            {
                fragment.setOnHeadlineSelectedListener(this)
            }
        }



        fun onArticleSelected(position: Int) {
            // The user selected the headline of an article from the HeadlinesFragment
            // Do something here to display that article

            val articleFrag = supportFragmentManager.findFragmentById(R.id.article_fragment) as ArticleFragment?

            if (articleFrag != null) {
                // If article frag is available, we're in two-pane layout...

                // Call a method in the ArticleFragment to update its content
                articleFrag.updateArticleView(position)
            } else {
                // Otherwise, we're in the one-pane layout and must swap frags...
                // Create fragment and give it an argument for the selected article
                val newFragment = ArticleFragment()
                val args = Bundle()
                args.putInt(ArticleFragment.ARG_POSITION, position)
                newFragment.arguments = args

                val transaction = supportFragmentManager.beginTransaction()

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.fragment_container, newFragment)
                transaction.addToBackStack(null)

                // Commit the transaction
                transaction.commit()
            }
        }
    }
}