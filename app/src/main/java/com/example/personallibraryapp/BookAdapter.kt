import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personallibraryapp.Book
import com.example.personallibraryapp.R

class BookAdapter(private val onClick: (Book) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    var books: List<Book> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false) // Ensure this layout exists
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = books.size

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle) // Adjust IDs as needed
        private val authorTextView: TextView = itemView.findViewById(R.id.textViewAuthor)
        private val pagesTextView: TextView = itemView.findViewById(R.id.textViewPages)

        fun bind(book: Book) {
            titleTextView.text = book.title
            authorTextView.text = book.author
            pagesTextView.text = book.pages.toString()
        }
    }
}
