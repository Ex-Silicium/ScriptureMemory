package com.exsilicium.scripturememory;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.exsilicium.scripture.shared.model.Book;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

final class BookViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_color) View colorView;
    @BindView(R.id.tv_book_name) TextView bookTitleTextView;

    private Book book;

    BookViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @ColorRes private static int getColorResForBook(Book book) {
        return book.isOldTestament() ? R.color.colorAccent : R.color.colorPrimary;
    }

    void bind(Book book) {
        this.book = book;
        colorView.setBackgroundColor(ContextCompat.getColor(colorView.getContext(), getColorResForBook(book)));
        bookTitleTextView.setText(book.getTitle());
    }

    @OnClick(R.id.ll_list_item_book) void onClickListItem(View view) {
        final Context context = view.getContext();
        final String abbreviationsText = context.getString(R.string.abbreviations_format, book.getAbbreviations().toString());
        Toast.makeText(context, abbreviationsText, Toast.LENGTH_LONG).show();
    }
}
