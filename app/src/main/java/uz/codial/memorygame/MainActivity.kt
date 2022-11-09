package uz.codial.memorygame

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var cardClickManager = false

    private val images = arrayListOf(
        R.drawable.card1,
        R.drawable.card1,
        R.drawable.card2,
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card5,
        R.drawable.card6,
        R.drawable.card6

    )
    private var countImage = 0
    private var photoId = arrayOfNulls<Int>(2)
    private var imageIndex = arrayOfNulls<Int>(2)
    private var imageUsed =
        arrayOf(false, false, false, false, false, false, false, false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restart()
    }

    fun restart() {
        images.shuffle()
        setClickListeners()
    }


    private fun start(image: ImageView, photo: Int, index: Int) {
        if (imageUsed[index]) {
            closeImage(image, index)
        } else {
            openImage(image, photo, index)
        }

    }

    private fun closeImage(image: ImageView, index: Int?) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.card_flip)
        image.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                cardClickManager = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.card_flip2)
                image.setImageResource(R.drawable.question_mark)
                image.startAnimation(animation2)
                imageUsed[index!!] = false
                countImage--
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        cardClickManager = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })


    }


    private fun openImage(image: ImageView, photo: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.card_flip)
        image.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                cardClickManager = true

            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.card_flip2)
                image.startAnimation(animation2)
                image.setImageResource(photo)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        imageUsed[index] = true
                        imageIndex[countImage] = index
                        photoId[countImage] = photo
                        countImage++

                        if (countImage == 2) {
                            if (photoId[0] == photoId[1]) {
                                findImageView(imageIndex[0]).visibility = View.INVISIBLE
                                countImage--
                                findImageView(imageIndex[1]).visibility = View.INVISIBLE
                                countImage--
                                if (!imageUsed.contains(false)) {
                                    for (i in imageUsed.indices) {
                                        imageUsed[i] = false
                                    }
                                    img0.visibility = View.VISIBLE
                                    img1.visibility = View.VISIBLE
                                    img2.visibility = View.VISIBLE
                                    img3.visibility = View.VISIBLE
                                    img4.visibility = View.VISIBLE
                                    img5.visibility = View.VISIBLE
                                    img6.visibility = View.VISIBLE
                                    img7.visibility = View.VISIBLE
                                    img8.visibility = View.VISIBLE
                                    img9.visibility = View.VISIBLE
                                    img10.visibility = View.VISIBLE
                                    img11.visibility = View.VISIBLE
                                    restart()
                                }

                            } else {
                                closeImage(findImageView(imageIndex[0]), imageIndex[0])
                                cardClickManager = true
                                closeImage(findImageView(imageIndex[1]), imageIndex[1])
                            }
                        }
                        cardClickManager = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                    }
                })


            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

    }


    fun findImageView(index: Int?): ImageView {
        var imageview = img0
        when (index) {
            0 -> imageview = img0
            1 -> imageview = img1
            2 -> imageview = img2
            3 -> imageview = img3
            4 -> imageview = img4
            5 -> imageview = img5
            6 -> imageview = img6
            7 -> imageview = img7
            8 -> imageview = img8
            9 -> imageview = img9
            10 -> imageview = img10
            11 -> imageview = img11
        }
        return imageview!!
    }


    private fun setClickListeners() {


        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                onTimerTick()
            }

            override fun onFinish() {
                onTimerFinish()
            }
        }.start()

    }

    private fun flipCards(image: ImageView) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.card_flip)
        image.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.card_flip2)
                image.setImageResource(R.drawable.question_mark)
                image.startAnimation(animation2)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }

    private fun onTimerTick() {

        img0.setImageResource(images[0])
        img1.setImageResource(images[1])
        img2.setImageResource(images[2])
        img3.setImageResource(images[3])
        img4.setImageResource(images[4])
        img5.setImageResource(images[5])
        img6.setImageResource(images[6])
        img7.setImageResource(images[7])

        img8.setImageResource(images[8])
        img9.setImageResource(images[9])
        img10.setImageResource(images[10])
        img11.setImageResource(images[11])

        img0.isClickable = false
        img1.isClickable = false
        img2.isClickable = false
        img3.isClickable = false
        img4.isClickable = false
        img5.isClickable = false
        img6.isClickable = false
        img7.isClickable = false
        img8.isClickable = false
        img9.isClickable = false
        img10.isClickable = false
        img11.isClickable = false
    }

    private fun onTimerFinish() {
        flipCards(img0)
        flipCards(img1)
        flipCards(img2)
        flipCards(img3)
        flipCards(img4)
        flipCards(img5)
        flipCards(img6)
        flipCards(img7)
        flipCards(img8)
        flipCards(img9)
        flipCards(img10)
        flipCards(img11)

        img0.isClickable = true
        img1.isClickable = true
        img2.isClickable = true
        img3.isClickable = true
        img4.isClickable = true
        img5.isClickable = true
        img6.isClickable = true
        img7.isClickable = true
        img8.isClickable = true
        img9.isClickable = true
        img10.isClickable = true
        img11.isClickable = true


    }

    fun cardClicked(view: View) {

        if (!cardClickManager) {
            cardClickManager = true
            val image = view as ImageView
            val tag = image.tag.toString().toInt()
            start(image, images[tag], tag)
        }


    }


}