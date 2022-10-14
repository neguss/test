package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.text.Layout
import android.widget.TextView
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv:TextView=TextView(this)
        var q= Array<Array<TextView>>(9,{Array(9,{tv})})
        var gen=Array<Array<Int>>(9,{Array(9,{0})})
        var transgen=Array<Array<Int>>(9,{Array(9,{0})})
        val buff= arrayOf(1,2,3,4,5,6,7,8,9)
        var blk= emptyArray<View>()
        val background=findViewById<ConstraintLayout>(R.id.main)
        var a =background.childCount
        var k:Int=0
        var n:Int=0
        var m:Int=0
        var rnd=Random(System.nanoTime())
        var ind=0
        //fill square need to check
        for(i in 0..8){
            for(j in 0..8){
                var mark=true
                k=rnd.nextInt(0,9)
                while (mark)
                {
                    if(!getrow(gen,i).contains(buff[k]) && !getcol(gen,j).contains(buff[k])){
                        if(i==j && (i==0||i==3||i==6)){
                                if(!checksquare(gen,i,j,buff[k])){
                                    gen[i][j]=buff[k]
                                    mark=false
                                }
                                else{
                                k=(k+1)/8
                            }
                        }
                        else{
                            gen[i][j]=buff[k]
                            mark=false
                        }
                    }
                    else{
                        k=(k+1)/8
                    }
                }
            }
        }
        //

        for (i in 0..8) {
            var tmp = getrow(gen, i)
            for (j in 0..8) {
                for (k in 0..8) {
                    if (tmp[j]==tmp[k] && tmp[j] != 0 && k!=j) {
                        gen[i][k] = 0
                        tmp[k]=0
                    }
                }
            }
        }

        for (i in 0..8) {
            var tmp = getcol(gen, i)
            for (j in 0..8) {
                for (k in 0..8) {
                    if (tmp[j]==tmp[k] && tmp[j] != 0 && k!=j) {
                        gen[k][i] = 0
                        tmp[k]=0
                    }
                }
            }
        }

        transgen=cast(gen)
        for (i in background.children)
        {
            blk+=i
            var w= emptyArray<TextView>()
            var block=findViewById<ConstraintLayout>(blk[n].id)
            m=0
            for (j in block.children) {
                var tmp:TextView=findViewById(j.id)
                if(transgen[n][m]!=0) {
                    tmp.text = transgen[n][m].toString()
                }
                else{
                    tmp.text=""
                }
                w+=tmp
                m++
            }
            q[n]=w
            n++
        }


        Toast.makeText(this,"123",Toast.LENGTH_SHORT)
    }
    /*
    00 01 02 03 04 05 06 07 08      00 01 02 10 11 12 20 21 22
    10 11 12 13 14 15 16 17 18      03 04 05 13 14 15 23 24 25
    20 21 22 23 24 25 26 27 28      06 07 08 16 17 18 26 27 28
    30 31 32 33 34 35 36 37 38      30 31 32 40 41 42 50 41 52
    40 41 42 43 44 45 46 47 48      33 34 35 43 44 45 53 54 55
    50 51 52 53 54 55 56 57 58      36 37 38 46 47 48 56 57 58
    60 61 62 63 64 65 66 67 68      60 61 62 70 71 72 80 81 82
    70 71 72 73 74 75 76 77 78      63 64 65 73 74 75 83 84 85
    80 81 82 83 84 85 86 87 88      66 67 68 76 77 78 86 87 88
     */
    fun cast(d:Array<Array<Int>>):Array<Array<Int>>{
        var c=Array<Array<Int>>(9,{Array(9,{0})})
        var n=0
        var m=2
        var k=0
        var l=2
        var q=0
        var w=0
        while (m<=8) {
            while (l <= 8) {
                for (i in n..m) {
                    for (j in k..l) {
                        c[q][w]=d[i][j]
                        w++
                    }
                }
                k += 3
                l += 3
                w=0
                q++
            }
            k=0
            l=2
            n+=3
            m+=3
            w=0
        }
        return c
        Toast.makeText(this,"123",Toast.LENGTH_SHORT)
    }
    fun getrow(d:Array<Array<Int>>,pos:Int):Array<Int>{
        var c=Array<Int>(d[pos].size,{0})
        for(i in d[pos].indices){
            c[i]=d[pos][i]
        }
        return c
    }
    fun getcol(d:Array<Array<Int>>,pos:Int):Array<Int>{
        var c=Array<Int>(d[pos].size,{0})
        for(i in d[pos].indices){
            c[i]=d[i][pos]
        }
        return c
    }
    fun getsquare(d:Array<Array<Int>>,posi:Int,posj:Int):Array<Array<Int>>{
        var c=Array<Array<Int>>(3,{Array(3,{0})})
        var q=0
        var w=0
        for(i in posi..posi+2){
            for(j in posj..posj+2){
                c[q][w]=d[i][j]
                w++
            }
            w=0
            q++
        }
        return c
    }
    fun checksquare(d:Array<Array<Int>>,posi:Int,posj:Int,e:Int):Boolean{
        var c=Array<Array<Int>>(3,{Array(3,{0})})
        var q=0
        var w=0
        var answer=false
        for(i in posi..posi+2){
            for(j in posj..posj+2){
                if(d[i][j]==e)
                    return true
            }
        }
        return answer
    }

}