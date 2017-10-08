package com.exsilicium.scripturememory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.exsilicium.scripture.shared.model.Book;

final class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private final Book[] values = Book.values();

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(values[position]);
    }

    @Override
    public int getItemCount() {
        return values.length;
    }
}
