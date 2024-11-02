package com.example.personallibraryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val onBookClick: (Book) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    var books: List<Book> = emptyList()

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val authorView: TextView = itemView.findViewById(R.id.textViewAuthor)
        private val pagesView: TextView = itemView.findViewById(R.id.textViewPages)
        private val readCheckbox: CheckBox = itemView.findViewById(R.id.checkboxRead)

        fun bind(book: Book) {
            titleView.text = book.title
            authorView.text = book.author
            pagesView.text = "${book.pages} pages"
            readCheckbox.isChecked = book.isRead
            itemView.setOnClickListener { onBookClick(book) }
            readCheckbox.setOnCheckedChangeListener { _, isChecked ->
                book.isRead = isChecked
                onBookClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size
}
