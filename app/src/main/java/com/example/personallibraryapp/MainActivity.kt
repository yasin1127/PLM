package com.example.personallibraryapp

import BookAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personallibraryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: BookViewModel by viewModels {
        BookViewModel.BookViewModelFactory(
            BookRepository(AppDatabase.getDatabase(this).bookDao())
        )
    }

    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookAdapter = BookAdapter { book ->
            viewModel.update(book)
        }

        binding.recyclerViewBooks.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.allBooks.observe(this, Observer { books ->
            bookAdapter.books = books
            bookAdapter.notifyDataSetChanged()
        })

        binding.buttonAdd.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val author = binding.editTextAuthor.text.toString()
            val pagesString = binding.editTextPages.text.toString() // Get pages as a string
            if (title.isNotEmpty() && author.isNotEmpty() && pagesString.isNotEmpty()) {
                val pages = pagesString.toIntOrNull() // Safely convert to Int
                if (pages != null) {
                    val book = Book(title = title, author = author, pages = pages, isRead = false)
                    viewModel.insert(book)

                    // Clear the input fields
                    binding.editTextTitle.text.clear()
                    binding.editTextAuthor.text.clear()
                    binding.editTextPages.text.clear()
                } else {
                    // Show an error for invalid page number
                    Toast.makeText(this, "Please enter a valid number of pages", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Show an error message for empty fields
                Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
            }

        }}}
