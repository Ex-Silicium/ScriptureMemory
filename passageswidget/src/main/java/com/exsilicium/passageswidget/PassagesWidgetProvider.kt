package com.exsilicium.passageswidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.exsilicium.persistence.MemoryPassageRepositoryDetailsDelegate
import dagger.android.AndroidInjection
import javax.inject.Inject

class PassagesWidgetProvider : AppWidgetProvider() {

    @Inject lateinit var memoryPassageRepositoryDetailsDelegate: MemoryPassageRepositoryDetailsDelegate

    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this, context)
        super.onReceive(context, intent)
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { updateWidget(context, appWidgetManager, it) }
    }

    private fun updateWidget(context: Context, appWidgetManager: AppWidgetManager, id: Int) {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_passages)
        // todo use https://google.github.io/dagger/android.html properly
//        memoryPassageRepositoryDetailsDelegate. // todo should I make a different "delegate" specific for the widget?
//        remoteViews.setTextViewText(R.id.tv_passage_content, passageContent)
//        remoteViews.setTextViewText(R.id.tv_passage_reference, passageReference)
    }
}