package com.gramzin.hackathon.data

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.kursx.parser.fb2.FictionBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.File
import java.io.FileOutputStream

object BookService {

    suspend fun getTextByUri(uri: Uri, ext: String, context: Context): String = withContext(Dispatchers.IO){
        val fileName = "book.$ext"
        val src = context.contentResolver.openInputStream(uri)!!;
        val dst = FileOutputStream(File(context.cacheDir, fileName))
        src.copyTo(dst)
        src.close()
        dst.close()

        val file = File("${context.cacheDir}/$fileName")

        return@withContext when(file.extension) {
            "fb2" -> readFb2Text(file)
            "epub" -> readEpubText(file)
            "txt" -> readTxtText(file, context)
            else -> throw Exception("A file with this extension is not supported")
        }
    }

    private fun readFb2Text(file: File): String{
        val book = FictionBook(file)
        val textOfBook = java.lang.StringBuilder()
        for (section in book.body.sections){
            for(element in section.elements){
                if (element.text.isNotEmpty() && element.text.last() == '-'){
                    textOfBook.append(element.text.dropLast(1))
                }
                else{
                    textOfBook.append(element.text + " ")
                }
            }
        }
        return textOfBook.toString()
    }

    private fun readEpubText(file: File): String{
        val epubReader = com.github.mertakdut.Reader()
        epubReader.setMaxContentPerSection(10000000)
        epubReader.setFullContent(file.absolutePath)
        var text1=""
        try {
            for (i in 0..Int.MAX_VALUE) {
                text1 += epubReader.readSection(i).sectionContent ?: ""
            }
        }
        catch (_: Exception){ }
        val doc = Jsoup.parse(text1)
        return doc.body().text()
    }

    private fun readTxtText(file: File, context: Context): String{
        val inputStream = context.contentResolver.openInputStream(file.toUri())!!
        val text = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        return text
    }

     fun getWordsContext(string: String, word: Word, offset: Int): MutableList<String>{
        var substrings = mutableListOf<String>()
        val wordList = word.children + word
        for(w in wordList){
            for (startIndex in w.indexes){
                var currentIndex = startIndex
                do{
                    val ch = string[currentIndex]
                    currentIndex++
                } while (ch.isLetter() && string.length>currentIndex)
                var left = startIndex - offset
                if (left< 0) left = 0

                var right = currentIndex + offset
                if (right > string.length) right = string.length
                substrings.add(string.substring(left, right))
            }
        }
        return substrings
    }
}