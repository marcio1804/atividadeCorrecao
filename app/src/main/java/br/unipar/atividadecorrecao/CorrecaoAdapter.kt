package br.unipar.atividadecorrecao

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CorrecaoAdapter(private val context: Context, private val dataList: MutableList<String>) :
    RecyclerView.Adapter<CorrecaoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editText: EditText = itemView.findViewById(R.id.editTextInput)
        val textView: TextView = itemView.findViewById(R.id.textViewOutput)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.editText.setText(dataList[position])
        holder.textView.text = dataList[position].length.toString()

        holder.editText.setOnKeyListener { _, keyCode, event ->
            if (event.action == android.view.KeyEvent.ACTION_DOWN && keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                holder.textView.text = holder.editText.text.toString()
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(holder.editText.windowToken, 0)
                true
            } else {
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
