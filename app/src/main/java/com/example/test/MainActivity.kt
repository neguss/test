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
import kotlinx.coroutines.selects.select
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
        var buff1= emptyArray<Int>()
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
            ind=rnd.nextInt(0,8)
            while(buff1.contains(buff[ind])){
                ind=(ind+1).mod(9)
            }
                buff1+=buff[ind]
        }

        for(i in 0..8){
            for(j in 0..8){
                when(i) {
                    0->gen[i][j] = buff1[j]
                    1->gen[i][j] = buff1[(j+3).mod(9)]
                    2->gen[i][j] = buff1[(j+6).mod(9)]
                    3->gen[i][j] = buff1[(j+1).mod(9)]
                    4->gen[i][j] = buff1[(j+4).mod(9)]
                    5->gen[i][j] = buff1[(j+7).mod(9)]
                    6->gen[i][j] = buff1[(j+2).mod(9)]
                    7->gen[i][j] = buff1[(j+5).mod(9)]
                    8->gen[i][j] = buff1[(j+8).mod(9)]
                }
            }
        }
        //transpose(gen)
        //swapcol(gen,0,2)
        //transpose(gen)
        //swaprow(gen,7,8)


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
        q = when (posi){
            0,1,2 -> 0
            3,4,5 -> 3
            else -> 6
        }
        w = when (posj){
            0,1,2 -> 0
            3,4,5 -> 3
            else -> 6
        }
        for(i in q..q+2){
            for(j in w..w+2){
                if(d[i][j]==e)
                    return true
            }
        }
        return false
    }
    fun swaprow(d:Array<Array<Int>>,first_row:Int,second_row:Int){
        for(i in 0..8){
            var tmp=d[first_row][i]
            d[first_row][i]=d[second_row][i]
            d[second_row][i]=tmp
        }
    }
    fun swapcol(d:Array<Array<Int>>,first_col:Int,second_col:Int){
        for(i in 0..8){
            var tmp=d[i][first_col]
            d[i][first_col]=d[i][second_col]
            d[i][second_col]=tmp
        }
    }
    fun transpose(d:Array<Array<Int>>){
        var tmp=d
        for(i in 0..8){
            for(j in 0..8){
                d[i][j]=tmp[j][i]
            }
        }

    }

}