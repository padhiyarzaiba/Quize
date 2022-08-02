package com.zaibap.quize.model
class QuestionModel{
    var question: String? = null
    var option1: String? = null
     var option2: String? = null
     var option3: String? = null
     var answerNr = 0

    constructor() {}
    constructor(
        question: String?,
        option1: String?,
        option2: String?,
        option3: String?,
        answerNr: Int
    ) {
        this.question = question
        this.option1 = option1
        this.option2 = option2
        this.option3 = option3
        this.answerNr = answerNr
    }
        @JvmName("getQuestion1")
        fun getQuestion(): String? {
        return question
    }

    @JvmName("setQuestion1")
    fun setQuestion(question: String?) {
        this.question = question
    }

    @JvmName("getOption11")
    fun getOption1(): String? {
        return option1
    }

    @JvmName("setOption11")
    fun setOption1(option1: String?) {
        this.option1 = option1
    }

    @JvmName("getOption21")
    fun getOption2(): String? {
        return option2
    }

    @JvmName("setOption21")
    fun setOption2(option2: String?) {
        this.option2 = option2
    }

    @JvmName("getOption31")
    fun getOption3(): String? {
        return option3
    }

    @JvmName("setOption31")
    fun setOption3(option3: String?) {
        this.option3 = option3
    }

    @JvmName("getAnswerNr1")
    fun getAnswerNr(): Int {
        return answerNr
    }

    @JvmName("setAnswerNr1")
    fun setAnswerNr(answerNr: Int) {
        this.answerNr = answerNr
    }

}
//class QuestionModel ( question :String,option1: String,option2: String,option3: String,answerNr:Int)
//
// {
//
//
//    private var question: String? = null
//    private var option1: String? = null
//    private var option2: String? = null
//    private var option3: String? = null
//    private var answerNr = 0
//
//    fun QuestionModel() {}
//
//    fun QuestionModel(
//        question: String?,
//        option1: String?,
//        option2: String?,
//        option3: String?,
//        answerNr: Int
//    ) {
//        this.question = question
//        this.option1 = option1
//        this.option2 = option2
//        this.option3 = option3
//        this.answerNr = answerNr
//    }
//
//    fun getQuestion(): String? {
//        return question
//    }
//
//    fun setQuestion(question: String?) {
//        this.question = question
//    }
//
//    fun getOption1(): String? {
//        return option1
//    }
//
//    fun setOption1(option1: String?) {
//        this.option1 = option1
//    }
//
//    fun getOption2(): String? {
//        return option2
//    }
//
//    fun setOption2(option2: String?) {
//        this.option2 = option2
//    }
//
//    fun getOption3(): String? {
//        return option3
//    }
//
//    fun setOption3(option3: String?) {
//        this.option3 = option3
//    }
//
//    fun getAnswerNr(): Int {
//        return answerNr
//    }
//
//    fun setAnswerNr(answerNr: Int) {
//        this.answerNr = answerNr
//    }
//}