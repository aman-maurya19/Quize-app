package aman.first.quizapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import kotlin.arrayOf

class MainActivity : AppCompatActivity() {

    private lateinit var defaultBackground: Drawable

    private lateinit var question: Array<String>
    private lateinit var option: Array<Array<String>>
    private lateinit var correctAnswer: Array<Int>

    private var currentQuestionIndex = 0
    private var score = 0
    private var answerSelected = false

    private var timer: CountDownTimer? = null
    private val totalTime = 30  // 30 sec timer


    private lateinit var tvQuestion: TextView
    private lateinit var tvOption1: TextView
    private lateinit var tvOption2: TextView
    private lateinit var tvOption3: TextView
    private lateinit var tvOption4: TextView
    private lateinit var nextBtn: MaterialButton
    private lateinit var progress: ProgressBar
    private lateinit var timerText: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvQuestion = findViewById(R.id.Question_text)
        tvOption1 = findViewById(R.id.textView1)
        tvOption2 = findViewById(R.id.textView2)
        tvOption3 = findViewById(R.id.textView3)
        tvOption4 = findViewById(R.id.textView4)
        nextBtn = findViewById(R.id.Next_button)
        progress = findViewById(R.id.progressBar)
        timerText = findViewById(R.id.timerText)

        val subject = intent.getStringExtra("subject")
        val level = intent.getStringExtra("level")

        setQuestions(subject, level)

        defaultBackground = tvOption1.background?.mutate()
            ?: resources.getDrawable(android.R.color.transparent, null)

        displayQuestion()

        tvOption1.setOnClickListener { checkAnswer(0) }
        tvOption2.setOnClickListener { checkAnswer(1) }
        tvOption3.setOnClickListener { checkAnswer(2) }
        tvOption4.setOnClickListener { checkAnswer(3) }

        nextBtn.setOnClickListener {
            if (!answerSelected) {
                Toast.makeText(this@MainActivity, "Please select an option first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            nextQuestion()
        }
    }

    private fun setQuestions(subject: String?, level: String?) {
        val sub = subject?.trim() ?: ""
        val lev = level?.trim() ?: ""

        when (sub) {

            // ---------------- C Programming ----------------
            "C Programming" -> {
                when (level) {
                    "beginner" -> {
                        question = arrayOf(
                            "Who developed C language?",
                            "C is a ___ language.",
                            "C program file extension is?",
                            "Which symbol ends a statement in C?",
                            "Which keyword is used to define constants?",
                            "Default return type of main() in C is?",
                            "Which function prints output?",
                            "Which character is used for newline?",
                            "Which datatype stores characters?",
                            "scanf() belongs to which header file?"
                        )

                        option = arrayOf(
                            arrayOf("James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido Rossum"),
                            arrayOf("High level", "Low level", "Middle level", "Machine level"),
                            arrayOf(".txt", ".cpp", ".c", ".java"),
                            arrayOf(",", ".", ";", ":"),
                            arrayOf("const", "constant", "define", "#define"),
                            arrayOf("void", "float", "int", "double"),
                            arrayOf("read()", "print()", "output()", "printf()"),
                            arrayOf("#", "\\n", "/", "\\t"),
                            arrayOf("int", "char", "string", "text"),
                            arrayOf("<stdlib.h>", "<conio.h>", "<stdio.h>", "<math.h>")
                        )

                        correctAnswer = arrayOf(1, 2, 2, 2, 3, 2, 3, 1, 1, 2)
                    }

                    "medium" -> {
                        question = arrayOf(
                            "Which loop executes at least once?",
                            "Which operator fetches value from the pointer?",
                            "malloc() belongs to which header?",
                            "Keyword to create a structure?",
                            "Array index starts from?",
                            "Entry point of C program?",
                            "Which pointer stores null?",
                            "Specifier for integer?",
                            "break is used for?",
                            "Size of int typically?"
                        )

                        option = arrayOf(
                            arrayOf("for", "while", "do-while", "foreach"),
                            arrayOf("*", "&", "&&", "->"),
                            arrayOf("<stdio.h>", "<stdlib.h>", "<string.h>", "<math.h>"),
                            arrayOf("struct", "structure", "object", "class"),
                            arrayOf("0", "1", "-1", "Random"),
                            arrayOf("start()", "begin()", "main()", "program()"),
                            arrayOf("void", "dangling", "wild", "NULL pointer"),
                            arrayOf("%d", "%c", "%i", "%s"),
                            arrayOf("stop loop", "skip", "repeat", "exit program"),
                            arrayOf("8 bytes", "4 bytes", "2 bytes", "1 byte")
                        )

                        correctAnswer = arrayOf(2, 0, 1, 0, 0, 2, 3, 0, 0, 1)
                    }

                    "hard" -> {
                        question = arrayOf(
                            "Where are local variables stored?",
                            "Dangling pointer means?",
                            "Which operator accesses structure via pointer?",
                            "Which feature is missing in C?",
                            "Stack overflow means?",
                            "string comparison function?",
                            "Fastest memory allocation?",
                            "Quick sort uses?",
                            "memset() belongs to?",
                            "Highest priority storage class?"
                        )

                        option = arrayOf(
                            arrayOf("Stack", "Heap", "CPU Register", "ROM"),
                            arrayOf("Assigned not declared", "Freed but used again", "Constant pointer", "Null pointer"),
                            arrayOf(".", "->", "*", "&"),
                            arrayOf("Recursion", "Pointers", "Inheritance", "Functions"),
                            arrayOf("Array full", "Stack memory full", "Heap full", "CPU overload"),
                            arrayOf("strcopy()", "strcmp()", "strmatch()", "strsame()"),
                            arrayOf("Heap", "Stack", "Static", "All equal"),
                            arrayOf("Divide and Conquer", "Greedy", "Backtracking", "DP"),
                            arrayOf("<memory.h>", "<string.h>", "<stdlib.h>", "<stdio.h>"),
                            arrayOf("auto", "register", "extern", "static")
                        )

                        correctAnswer = arrayOf(0, 1, 1, 2, 1, 1, 1, 0, 1, 1)
                    }
                }
            }

            // ---------------- Java Programming ----------------
            "Java Programming" -> {
                when (level) {
                    "beginner" -> {
                        question = arrayOf(
                            "Java was developed by?",
                            "Java is?",
                            "JVM stands for?",
                            "Source extension?",
                            "Keyword for inheritance?",
                            "Entry method?",
                            "Not a datatype?",
                            "OOP stands for?",
                            "Compiled to?",
                            "Developed by?"
                        )

                        option = arrayOf(
                            arrayOf("Ritchie", "Gosling", "Ken", "Sundar"),
                            arrayOf("Compiled", "Interpreted", "Both", "None"),
                            arrayOf("Visual", "Virtual Model", "Virtual Machine", "Version Machine"),
                            arrayOf(".jav", ".java", ".j", ".class"),
                            arrayOf("inherit", "extends", "super", "this"),
                            arrayOf("start()", "main()", "run()", "execute()"),
                            arrayOf("int", "float", "char", "digit"),
                            arrayOf("Object Oriented Programming", "Platform", "Process", "None"),
                            arrayOf("Source", "Bytecode", "Machine", "Binary"),
                            arrayOf("Google", "Sun Microsystems", "Microsoft", "IBM")
                        )

                        correctAnswer = arrayOf(1, 2, 2, 1, 1, 1, 3, 0, 1, 1)
                    }

                    "medium" -> {
                        question = arrayOf(
                            "Prevent inheritance keyword?",
                            "Object creation operator?",
                            "No duplicates collection?",
                            "Parent of all classes?",
                            "Not OOP concept?",
                            "Executes at least once?",
                            "Collection framework in?",
                            "String is?",
                            "Exception handled with?",
                            "Parent constructor call?"
                        )

                        option = arrayOf(
                            arrayOf("final", "static", "const", "private"),
                            arrayOf("new", "create", "alloc", "init"),
                            arrayOf("ArrayList", "HashSet", "Stack", "Vector"),
                            arrayOf("System", "Object", "Main", "Class"),
                            arrayOf("Inheritance", "Polymorphism", "Abstraction", "Compilation"),
                            arrayOf("for", "while", "do-while", "foreach"),
                            arrayOf("java.lang", "java.net", "java.util", "java.io"),
                            arrayOf("Mutable", "Immutable", "Dynamic", "Primitive"),
                            arrayOf("if", "try-catch", "switch", "while"),
                            arrayOf("parent", "base", "super", "default")
                        )

                        correctAnswer = arrayOf(0, 0, 1, 1, 3, 2, 2, 1, 1, 2)
                    }

                    "hard" -> {
                        question = arrayOf(
                            "Singleton pattern type?",
                            "Generic type keyword?",
                            "Collections.sort() uses?",
                            "Multi-threading?",
                            "Who handles thread scheduling?",
                            "Reflection API in?",
                            "GC algorithm?",
                            "Suppress warnings annotation?",
                            "Keyword for sync block?",
                            "JIT improves?"
                        )

                        option = arrayOf(
                            arrayOf("Creational", "Structural", "Behavioral", "Factory"),
                            arrayOf("<T>", "<G>", "var", "{T}"),
                            arrayOf("Comparable", "Comparator", "Iterable", "Collection"),
                            arrayOf("Thread + Runnable", "Observer", "Service", "Reflection"),
                            arrayOf("ThreadScheduler", "Scheduler", "JVM", "JRE"),
                            arrayOf("java.reflect", "java.class", "java.core", "java.annotation"),
                            arrayOf("Mark-Sweep", "Stop-The-World", "Copy", "All"),
                            arrayOf("@Ignore", "@SuppressWarnings", "@Override", "@Remove"),
                            arrayOf("sync", "synchronized", "thread", "block"),
                            arrayOf("Memory", "CPU", "Runtime", "Compile speed")
                        )

                        correctAnswer = arrayOf(0, 0, 0, 0, 2, 0, 3, 1, 1, 2)
                    }
                }
            }

            // ---------------- Python Programming ----------------
            "Python Programming" -> {
                when (level) {
                    "beginner" -> {
                        question = arrayOf(
                            "Python was developed by?",
                            "Extension of Python file?",
                            "Python is?",
                            "Keyword for function?",
                            "Symbol for comment?",
                            "Output of print(10==10)?",
                            "Mutable datatype?",
                            "Loop keyword?",
                            "Exponent operator?",
                            "Input function?"
                        )

                        option = arrayOf(
                            arrayOf("Gosling", "Guido Rossum", "Ritchie", "Stroustrup"),
                            arrayOf(".py", ".pt", ".java", ".txt"),
                            arrayOf("Low level", "Middle", "High level", "Machine"),
                            arrayOf("function", "method", "def", "fun"),
                            arrayOf("#", "*", "//", "!"),
                            arrayOf("True", "False", "None", "Error"),
                            arrayOf("Tuple", "List", "String", "Int"),
                            arrayOf("until", "loop", "for", "iterate"),
                            arrayOf("^", "**", "%%", "&&"),
                            arrayOf("scanner()", "cin>>", "input()", "read()")
                        )

                        correctAnswer = arrayOf(1, 0, 2, 2, 0, 0, 1, 2, 1, 2)
                    }

                    "medium" -> {
                        question = arrayOf(
                            "class is created using?",
                            "OOP stands for?",
                            "__init__ is?",
                            "Inheritance symbol?",
                            "Unique items collection?",
                            "Datetime module?",
                            "Length function?",
                            "Dictionary stores?",
                            "Exception handling?",
                            "Membership operator?"
                        )

                        option = arrayOf(
                            arrayOf("class", "define", "struct", "declare"),
                            arrayOf("Object Oriented Programming", "Open Optional Program", "Output Program", "None"),
                            arrayOf("Constructor", "Destructor", "Attribute", "Event"),
                            arrayOf(":", ".", "()", "()()"),
                            arrayOf("List", "Tuple", "Set", "String"),
                            arrayOf("timing", "date", "datetime", "calender"),
                            arrayOf("size()", "length()", "len()", "count()"),
                            arrayOf("Key-value", "Only values", "Only keys", "Single value"),
                            arrayOf("except", "try-catch", "handle", "stop"),
                            arrayOf("==", "in", "~", "|")
                        )

                        correctAnswer = arrayOf(0, 0, 0, 0, 2, 2, 2, 0, 0, 1)
                    }

                    "hard" -> {
                        question = arrayOf(
                            "lambda means?",
                            "Delete object keyword?",
                            "Virtual environment?",
                            "Generator keyword?",
                            "Multithreading module?",
                            "Same method diff forms?",
                            "Prevents overriding?",
                            "Async supported by?",
                            "Numerical computing library?",
                            "Python code runs on?"
                        )
                        option = arrayOf(
                            arrayOf("Anonymous function", "Private", "Static", "Special"),
                            arrayOf("destruct", "remove", "del", "kill"),
                            arrayOf("pip create", "venv", "virtual", "python create"),
                            arrayOf("yield", "gen", "return", "yieldfrom"),
                            arrayOf("thread", "multicore", "threading", "core"),
                            arrayOf("Inheritance", "Encapsulation", "Polymorphism", "Abstraction"),
                            arrayOf("constant", "sealed", "prevent", "static"),
                            arrayOf("asyncio", "async", "sleep", "timer"),
                            arrayOf("NumPy", "Panda", "Random", "Calc"),
                            arrayOf("Interpreter", "Compiler", "Assembler", "Mixin system")
                        )
                        correctAnswer = arrayOf(0, 2, 1, 0, 2, 2, 1, 0, 0, 0)
                    }
                }
            }

            //........................C++ Programming........................
            "C++ Programming" -> {
                when(level){
                    "beginner" -> {
                        question = arrayOf(
                            "C++ was developed by?",
                            "C++ is also known as?",
                            "File extension of C++ is?",
                            "Which symbol ends a statement in C++?",
                            "Which header file is used for input output?",
                            "Which keyword is used to create object?",
                            "Which operator is used for address of variable?",
                            "Which is correct to print output?",
                            "Which is used to take input from user?",
                            "Which method is entry point of C++ program?"
                        )

                        option = arrayOf(
                            arrayOf("Dennis Ritchie","Bjarne Stroustrup","James Gosling", "Ken Thompson"),
                            arrayOf("Object oriented language", "Functional language", "Static language", "Dynamic language"),
                            arrayOf(".cp", ".cpp", ".cplus", ".c"),
                            arrayOf(":", ";", ".", ","),
                            arrayOf("<stdio.h>", "<conio.h>", "<iostream>", "<ctype.h>"),
                            arrayOf("new", "create", "make", "object"),
                            arrayOf("&", "*", "^", "@"),
                            arrayOf("printf", "print", "cout <<", "output"),
                            arrayOf("get", "cin >>", "read()", "take()"),
                            arrayOf("main()", "program()", "run()", "entry()")
                        )

                        correctAnswer = arrayOf(1, 0, 1, 1, 2, 0, 0, 2, 1, 0)
                    }
                    "medium"->{
                        question = arrayOf(
                            "Which concept allows data hiding?",
                            "Which operator is used to access members of object?",
                            "Which keyword is used for inheritance?",
                            "Which function frees dynamically allocated memory?",
                            "Which loop executes at least one time?",
                            "Which feature supports compile time polymorphism?",
                            "Which container is part of STL?",
                            "Which of these is reference variable?",
                            "Which casts one type to another?",
                            "Which is used for function overloading?"
                        )

                        option = arrayOf(
                            arrayOf("Abstraction", "Encapsulation", "Polymorphism", "Inheritance"),
                            arrayOf(".", "::", "->", "::>"),
                            arrayOf("extends", "inherit", "public", "base"),
                            arrayOf("free()", "delete", "remove", "destroy"),
                            arrayOf("for", "while", "do-while", "switch"),
                            arrayOf("Virtual function", "Operator overloading", "Friend function", "Inline function"),
                            arrayOf("Array", "Vector", "String literal", "Pointer"),
                            arrayOf("*p", "p()", "&p", "p&"),
                            arrayOf("convert()", "static_cast", "castto", "typeof"),
                            arrayOf("Two functions with same name but different parameters", "Two classes with same name", "Two data types same", "Two objects same")
                        )

                        correctAnswer = arrayOf(1, 0, 2, 1, 2, 1, 1, 3, 1, 0)
                    }

                    "hard" -> {
                        question = arrayOf(
                            "Runtime polymorphism uses?",
                            "Which statement prevents inheritance?",
                            "Which type of inheritance has one base and multiple derived?",
                            "Which concept resolves ambiguity in multiple inheritance?",
                            "Which class cannot be instantiated?",
                            "Which operator cannot be overloaded?",
                            "Which template enables generic programming?",
                            "Which pointer contains null value by default?",
                            "Which keyword is used to return current object?",
                            "Which model of memory allocation uses heap?"
                        )
                        option = arrayOf(
                            arrayOf("Function overloading", "Virtual function", "Inline function", "Operator overloading"),
                            arrayOf("sealed", "final", "stop", "end"),
                            arrayOf("Multilevel", "Multiple", "Hybrid", "Hierarchical"),
                            arrayOf("Virtual base class", "Virtual pointer", "Friend class", "Static class"),
                            arrayOf("Abstract class", "Normal class", "Template class", "Static class"),
                            arrayOf("=", "+", "::", "++"),
                            arrayOf("class template", "general template", "system template", "type template"),
                            arrayOf("Garbage pointer", "Wild pointer", "Void pointer", "Smart pointer"),
                            arrayOf("this", "self", "me", "object"),
                            arrayOf("Dynamic allocation", "Static allocation", "Auto allocation", "Global allocation")
                        )
                        correctAnswer = arrayOf(1, 1, 3, 0, 0, 2, 0, 0, 0, 0)
                    }
                }
            }

            //.........................Kotlin Programming........................
            "Kotlin Programming" -> {
                when(level){
                    "beginner" -> {
                        question = arrayOf(
                            "Kotlin was developed by?",
                            "Kotlin is officially supported by Google from which year?",
                            "File extension of Kotlin is?",
                            "Which keyword is used to declare variable?",
                            "Which function prints output in Kotlin?",
                            "Which type is used to store decimal numbers?",
                            "Default value of Boolean variable is?",
                            "Which method is entry point in Kotlin program?",
                            "Which is used for string concatenation in Kotlin?",
                            "Correct way to define constant value?"
                        )

                        option = arrayOf(
                            arrayOf("Microsoft", "JetBrains", "Meta", "Oracle"),
                            arrayOf("2013", "2015", "2017", "2019"),
                            arrayOf(".kt", ".kot", ".kotlin", ".kl"),
                            arrayOf("var", "int", "value", "new"),
                            arrayOf("System.out.print", "println()", "printOut()", "log()"),
                            arrayOf("Int", "Double", "Char", "Byte"),
                            arrayOf("true", "false", "null", "none"),
                            arrayOf("main()", "Main()", "start()", "onRun()"),
                            arrayOf("+", "&", "@", "#"),
                            arrayOf("const val", "static var", "final let", "constant")
                        )

                        correctAnswer = arrayOf(1, 2, 0, 0, 1, 1, 1, 0, 0, 0)
                    }
                        "medium" -> {
                            question = arrayOf(
                                "Which keyword is used for inheritance in Kotlin?",
                                "Which feature removes null pointer exceptions?",
                                "Which collection is immutable?",
                                "Which operator is used for null safety check?",
                                "Which function runs only for non null values?",
                                "Which converts any type to string?",
                                "Which scope function returns lambda result?",
                                "Which class cannot be subclassed?",
                                "Which is used to implement interfaces?",
                                "Which is used to create singleton?"
                            )
                            option = arrayOf(
                                arrayOf("extends", "open", "inherit", ":"),
                                arrayOf("Smart cast", "Safe call", "Null safety", "Type inference"),
                                arrayOf("mutableListOf", "arrayListOf", "listOf", "hashMapOf"),
                                arrayOf("?.", "!!", "::", "?-"),
                                arrayOf("let", "run", "apply", "with"),
                                arrayOf("toString()", "cast()", "asText()", "string()"),
                                arrayOf("also", "let", "run", "apply"),
                                arrayOf("open class", "static class", "final class", "abstract class"),
                                arrayOf("by", "for", "using", "assign"),
                                arrayOf("object", "class", "module", "unique")
                            )
                            correctAnswer = arrayOf(3, 2, 2, 0, 0, 0, 2, 2, 0, 0)
                        }

                    "hard" -> {

                        question = arrayOf(
                            "Which type is used when type is unknown?",
                            "Which function is a suspend function executed asynchronously?",
                            "Which keyword stops further overriding?",
                            "Coroutine scope without lifecycle is?",
                            "Which dispatcher performs CPU heavy work?",
                            "Which creates data class copy with modifications?",
                            "Which keyword is used for sealed class?",
                            "Which annotation prevents function inlining?",
                            "Which handles multiple return values from functions?",
                            "Which used to map flow values to new values?"
                        )

                        option = arrayOf(
                            arrayOf("Nothing", "Unit", "Any", "Unknown"),
                            arrayOf("launch()", "async()", "delay()", "runBlocking()"),
                            arrayOf("static", "final", "sealed", "stop"),
                            arrayOf("GlobalScope", "ViewModelScope", "MainScope", "IOscope"),
                            arrayOf("Dispatchers.IO", "Dispatchers.Main", "Dispatchers.Default", "Dispatchers.Unconfined"),
                            arrayOf("edit()", "modify()", "copy()", "change()"),
                            arrayOf("extends", "perm", "sealed", "only"),
                            arrayOf("noinline", "inline", "restricted", "block"),
                            arrayOf("List", "Pair", "Set", "Map"),
                            arrayOf("map()", "catch()", "filter()", "collect()")
                        )
                        correctAnswer = arrayOf(2, 1, 1, 0, 2, 2, 2, 0, 1, 0)

                    }
                }
            }
            //.......................JavaScript Programming............................
            "JavaScript Programming" -> {
                when(level){
                    "beginner" -> {
                        question = arrayOf(
                            "JavaScript was developed by?",
                            "JavaScript is a ___ language.",
                            "JavaScript file extension is?",
                            "Which keyword declares a variable?",
                            "Which method prints output in console?",
                            "Which symbol is used to end a statement?",
                            "Which function converts string to number?",
                            "Which tag is used to write JavaScript in HTML?",
                            "Which operator is used for addition?",
                            "Which is not a JavaScript datatype?"
                        )

                        option = arrayOf(
                            arrayOf("Dennis Ritchie", "Brendan Eich", "James Gosling", "Tim Berners Lee"),
                            arrayOf("Compiled", "Interpreted", "Both", "None"),
                            arrayOf(".js", ".java", ".jsp", ".script"),
                            arrayOf("var", "int", "value", "dim"),
                            arrayOf("print()", "log()", "console.log()", "show()"),
                            arrayOf(".", ";", ",", ":"),
                            arrayOf("parseInt()", "stringToInt()", "convert()", "num()"),
                            arrayOf("<javascript>", "<script>", "<js>", "<code>"),
                            arrayOf("+", "-", "*", "/"),
                            arrayOf("number", "string", "boolean", "digit")
                        )

                        correctAnswer = arrayOf(1, 1, 0, 0, 2, 1, 0, 1, 0, 3)
                    }
                    "medium"->{
                        question = arrayOf(
                            "Which keyword creates a constant variable?",
                            "Which operator checks both value and datatype?",
                            "Which method joins array elements into a string?",
                            "Which function is used to handle exceptions?",
                            "DOM stands for?",
                            "Which returns number of elements in array?",
                            "Which is used to convert JSON to object?",
                            "Which loop iterates over array values?",
                            "Which removes last element from array?",
                            "Which feature allows writing function inside a function?"
                        )

                        option = arrayOf(
                            arrayOf("var", "let", "constant", "const"),
                            arrayOf("==", "===", "!=", "="),
                            arrayOf("join()", "combine()", "attach()", "merge()"),
                            arrayOf("try catch", "if else", "switch", "throw only"),
                            arrayOf("Data Object Model", "Document Object Model", "Dynamic Object Mapping", "Digital Object Memory"),
                            arrayOf("total", "size", "length", "count"),
                            arrayOf("JSON.parse()", "JSON.decode()", "parseJSON()", "readJSON()"),
                            arrayOf("for", "for in", "for of", "loop each"),
                            arrayOf("remove()", "delete()", "pop()", "clear()"),
                            arrayOf("Inheritance", "Scoped functions", "Closures", "Callbacks")
                        )

                        correctAnswer = arrayOf(3, 1, 0, 0, 1, 2, 0, 2, 2, 2)
                    }
                    "hard"-> {
                        question = arrayOf(
                            "Which defines asynchronous operations in JavaScript?",
                            "Which keyword is used with promises?",
                            "Which object is used to handle events?",
                            "Which function schedules code execution?",
                            "Which removes duplicate values from an array?",
                            "Which is used for deep copy?",
                            "Which JavaScript engine is used in Google Chrome?",
                            "Which attribute prevents event bubbling?",
                            "Which checks if object contains a property?",
                            "Which converts array of key value pairs to object?"
                        )

                        option = arrayOf(
                            arrayOf("Callbacks", "Promises", "Async Await", "All of the above"),
                            arrayOf("wait", "async", "await", "delay"),
                            arrayOf("Event", "Trigger", "Emitter", "Dispatcher"),
                            arrayOf("setTime()", "setTimer()", "setTimeout()", "schedule()"),
                            arrayOf("unique()", "removeRepeat()", "Set()", "distinct()"),
                            arrayOf("copyData()", "JSON.parse(JSON.stringify())", "objCopy()", "clone()"),
                            arrayOf("V8", "SpiderMonkey", "JSCore", "Chakra"),
                            arrayOf("stopBubble", "noBubble", "stopPropagation", "stopEvent"),
                            arrayOf("has()", "in", "property()", "contains()"),
                            arrayOf("makeObject()", "assign()", "fromEntries()", "toObject()")
                        )

                        correctAnswer = arrayOf(3, 2, 0, 2, 2, 1, 0, 2, 1, 2)

                    }
                }
            }
            //....................HTML Programming........................
            "HTML Programming" -> {
                when(level) {
                    "beginner" -> {
                        question = arrayOf(
                            "HTML stands for?",
                            "Which tag is used for the largest heading?",
                            "Which tag inserts a line break?",
                            "Which attribute adds a link to <a> tag?",
                            "Which tag is used to display an image?",
                            "Which tag creates a paragraph?",
                            "HTML file extension is?",
                            "Which tag makes text bold?",
                            "Which tag creates an ordered list?",
                            "Which tag is used to display a table row?"
                        )

                        option = arrayOf(
                            arrayOf("Hyper Trainer Marking Language", "Hyper Text Markup Language", "High Text Machine Language", "Hyperlinks Text Marking Language"),
                            arrayOf("<h5>", "<h4>", "<h1>", "<head>"),
                            arrayOf("<p>", "<lb>", "<br>", "<b>"),
                            arrayOf("src", "href", "link", "ref"),
                            arrayOf("<src>", "<img>", "<image>", "<pic>"),
                            arrayOf("<text>", "<p>", "<para>", "<pg>"),
                            arrayOf(".txt", ".html", ".htx", ".doc"),
                            arrayOf("<strong>", "<b>", "<bold>", "<high>"),
                            arrayOf("<ul>", "<list>", "<ol>", "<order>"),
                            arrayOf("<tr>", "<table>", "<td>", "<col>")
                        )

                        correctAnswer = arrayOf(1, 2, 2, 1, 1, 1, 1, 1, 2, 0)
                    }
                    "medium"->{
                        question = arrayOf(
                            "Which tag is used to create a dropdown list?",
                            "Which attribute is used to provide alternative text for images?",
                            "Which tag is used to insert audio?",
                            "Which tag is used to group rows in a table body?",
                            "Which tag represents a navigation section?",
                            "Which input type is used for password field?",
                            "Which tag defines a footer for a document?",
                            "Which attribute is used to merge table columns?",
                            "Which tag creates a checkbox?",
                            "Which attribute opens link in new tab?"
                        )
                        option = arrayOf(
                            arrayOf("<select>", "<dropdown>", "<option>", "<menu>"),
                            arrayOf("alt", "title", "des", "name"),
                            arrayOf("<sound>", "<voice>", "<music>", "<audio>"),
                            arrayOf("<body>", "<tbody>", "<tablebody>", "<group>"),
                            arrayOf("<nav>", "<navigate>", "<menu>", "<route>"),
                            arrayOf("type=email", "type=code", "type=text", "type=password"),
                            arrayOf("<bottom>", "<footer>", "<foot>", "<end>"),
                            arrayOf("merge", "span", "colspan", "rowspan"),
                            arrayOf("<radio>", "<box>", "<input type=checkbox>", "<check>"),
                            arrayOf("load=new", "open=true", "target=blank", "href=another")
                        )
                        correctAnswer = arrayOf(0, 0, 3, 1, 0, 3, 1, 2, 2, 2)

                    }
                    "hard"->{
                        question = arrayOf(
                            "Which tag is used to draw graphics using JavaScript?",
                            "Which tag is used to define metadata?",
                            "Which HTML API provides persistent local storage?",
                            "Which attribute is used for responsive image scaling?",
                            "Which tag defines self contained content?",
                            "Which attribute disables form input?",
                            "Which element is used for semantic emphasis?",
                            "Which protocol is used in video streaming in HTML5?",
                            "Which tag is used to define client side script?",
                            "Which attribute is used to validate form before submitting?"
                        )

                        option = arrayOf(
                            arrayOf("<draw>", "<svg>", "<canvas>", "<paint>"),
                            arrayOf("<meta>", "<data>", "<mtd>", "<script>"),
                            arrayOf("Session Storage", "File System", "IndexedDB", "Local Storage"),
                            arrayOf("responsive", "fit", "srcset", "scale"),
                            arrayOf("<content>", "<standalone>", "<article>", "<section>"),
                            arrayOf("disable", "nopress", "readonly", "disabled"),
                            arrayOf("<i>", "<em>", "<italic>", "<stress>"),
                            arrayOf("HLS", "FTP", "POP3", "SMTP"),
                            arrayOf("<javascript>", "<script>", "<js>", "<code>"),
                            arrayOf("validation", "required", "check", "onsubmit")
                        )
                        correctAnswer = arrayOf(2, 0, 3, 2, 2, 3, 1, 0, 1, 3)
                    }
                }
            }
            //.......................CSS Programming........................
            "CSS Programming" -> {
                when(level) {
                    "beginner" -> {
                        question = arrayOf(
                            "What is the full form of CSS?",
                            "Which attribute is used to apply CSS inside HTML?",
                            "What is the extension of an external CSS file?",
                            "Which property makes text bold?",
                            "Which property is used to change the background color?",
                            "Which property changes the size of the text?",
                            "What does padding do?",
                            "Which property is used to make corners rounded?",
                            "Which symbol is used to select an ID in CSS?",
                            "Which symbol is used to select a class in CSS?"
                        )
                        option = arrayOf(
                            arrayOf("Cascading Style Sheets", "Creative Style Script", "Color Style System", "Coding Style Sheet"),
                            arrayOf("design", "style", "class", "css"),
                            arrayOf(".html", ".java", ".css", ".txt"),
                            arrayOf("font-style", "font-weight", "text-bold", "text-weight"),
                            arrayOf("background-color", "bg", "color-bg", "bg-color"),
                            arrayOf("font-size", "text-size", "size-font", "font-text"),
                            arrayOf("Inner space", "Outer space", "Shadow effect", "Background image"),
                            arrayOf("border-round", "border-radius", "round-border", "radius-border"),
                            arrayOf(".", "#", "*", "@"),
                            arrayOf(".", "#", "*", "$")
                        )
                        correctAnswer = arrayOf(0, 1, 2, 1, 0, 0, 0, 1, 1, 0)
                    }

                    "medium" -> {
                        question = arrayOf(
                            "Which display value activates Flexbox?",
                            "Which property is used to define rows in CSS Grid?",
                            "Position: absolute is relative to?",
                            "What is the purpose of z-index?",
                            "Which selector is used to apply hover effect?",
                            "What does box-shadow do?",
                            "What is the use of transition property?",
                            "How is a CSS variable defined?",
                            "Which property creates spacing between items in Flexbox?",
                            "Which property is used to center text horizontally?"
                        )
                        option = arrayOf(
                            arrayOf("block", "flex", "inline", "grid"),
                            arrayOf("grid-columns", "grid-rows", "display-grid", "grid-line"),
                            arrayOf("viewport", "nearest positioned parent", "browser top", "body always"),
                            arrayOf("font size", "layer order", "opacity", "animation speed"),
                            arrayOf(":active", ":hover", ":click", ":move"),
                            arrayOf("Border color", "Shadow around box", "Page layout", "Font decoration"),
                            arrayOf("Movement delay", "Color of element", "Smooth change effect", "Shadow effect"),
                            arrayOf("--var", "variable", "css-var()", "var-define"),
                            arrayOf("margin", "space", "gap", "padding"),
                            arrayOf("text-center", "align", "text-align", "justify")
                        )
                        correctAnswer = arrayOf(1, 1, 1, 1, 1, 1, 2, 0, 2, 2)
                    }
                    "hard"->{
                        question = arrayOf(
                            "Which has the highest priority in CSS specificity?",
                            "Which of the following is a pseudo-element?",
                            "Which rule is used to create custom animation?",
                            "What is the purpose of calc() in CSS?",
                            "The rem unit is based on?",
                            "Which property is used for clipping?",
                            "What does backdrop-filter do?",
                            "What is the best way to vertically center content?",
                            "Which keyword is used in media query?",
                            "Which property helps reset the stacking (layering) context?"
                        )
                        option = arrayOf(
                            arrayOf("Class", "ID", "Universal selector", "Inline style"),
                            arrayOf(":hover", "::before", ":visited", ":focus"),
                            arrayOf("@media", "@keyframes", "@import", "@animation"),
                            arrayOf("Calculation for values", "Color change", "Font formatting", "Shadow creation"),
                            arrayOf("Viewport size", "Root font size", "Element height", "Parent width"),
                            arrayOf("clip-path", "clip-image", "crop-border", "border-cut"),
                            arrayOf("Text shadow", "Blur background", "Image rotate", "Font resize"),
                            arrayOf("padding + padding", "margin auto flexbox", "absolute position", "text justify"),
                            arrayOf("between", "query", "screen", "value"),
                            arrayOf("z-index reset", "reset-layer", "isolate", "clear-layer")
                        )
                        correctAnswer = arrayOf(3, 1, 1, 0, 1, 0, 1, 1, 2, 2)
                    }
                }
            }
            //....................PHP programming..................
            "PHP Programming" -> {
                when (level) {
                    "beginner" -> {
                        question = arrayOf(
                            "PHP stands for?",
                            "Which symbol is used to start a variable in PHP?",
                            "Which tag is used to start PHP code?",
                            "Which function is used to print output in PHP?",
                            "Which data type is used for true or false?",
                            "Which operator is used for string concatenation?",
                            "Which extension is correct for a PHP file?",
                            "Which keyword is used to define a function in PHP?",
                            "Which function is used to get the length of a string?",
                            "Which of the following is a correct array type in PHP?"
                        )
                        option = arrayOf(
                            arrayOf("Personal Home Page", "Programming Hyper Processor", "Private Home Page", "Page Home Program"),
                            arrayOf("&", "$", "#", "@"),
                            arrayOf("<?php", "<php>", "<?", "<code>"),
                            arrayOf("echo", "printline", "println", "display"),
                            arrayOf("Integer", "Boolean", "Float", "Double"),
                            arrayOf("*", "+", ".", ","),
                            arrayOf(".htm", ".php", ".src", ".py"),
                            arrayOf("method", "define", "function", "fun"),
                            arrayOf("count()", "len()", "strlen()", "length()"),
                            arrayOf("Indexed", "Associative", "Multidimensional", "All of the above")
                        )
                        correctAnswer = arrayOf(0, 1, 0, 0, 1, 2, 1, 2, 2, 3)
                    }
                    "medium"->{
                        question = arrayOf(
                            "Which superglobal variable is used to collect form data sent with POST?",
                            "Which one is used to connect PHP with MySQL?",
                            "Which function is used to include a file in PHP only once?",
                            "Which loop executes at least one time in PHP?",
                            "Which method is used to destroy a session?",
                            "Which statement is used to stop further script execution?",
                            "Which superglobal stores values passed in URL?",
                            "Which function is used to convert a string to lowercase?",
                            "Which keyword is used to inherit a class?",
                            "Which function is used to redirect to another web page?"
                        )
                        option = arrayOf(
                            arrayOf("\$_FORM", "\$_REQUEST", "\$_POST", "\$_DATA"),
                            arrayOf("mysql_build()", "mysqli_connect()", "connect_mysql()", "database_connect()"),
                            arrayOf("require", "include", "include_once", "load_once"),
                            arrayOf("for", "foreach", "do-while", "while"),
                            arrayOf("remove()", "stop()", "session_destroy()", "destroy_session()"),
                            arrayOf("stop_script()", "break", "exit()", "stop()"),
                            arrayOf("\$_GET", "\$_URL", "\$_POST", "\$_PAGE"),
                            arrayOf("strlower()", "tolower()", "lower()", "strtolower()"),
                            arrayOf("inherits", "extends", "implement", "use"),
                            arrayOf("go()", "navigate()", "header()", "redirect()")
                        )
                        correctAnswer = arrayOf(2, 1, 2, 2, 2, 2, 0, 3, 1, 2)
                    }
                    "hard"->{
                        question = arrayOf(
                            "Which OOP feature allows a class to have multiple methods with same name but different parameters?",
                            "Which magic method is called when an object is created?",
                            "Which keyword prevents a class from being inherited?",
                            "Which operator compares value and data type?",
                            "What is PDO mainly used for?",
                            "Which magic constant returns the current line number of file?",
                            "Which function is used to hash a password?",
                            "Which keyword is used to implement multiple interfaces?",
                            "Which type of error stops script execution immediately?",
                            "Which function is used to serialize data?"
                        )
                        option = arrayOf(
                            arrayOf("Inheritance", "Polymorphism", "Abstraction", "Encapsulation"),
                            arrayOf("__start()", "__construct()", "__create()", "__object()"),
                            arrayOf("stop", "restrict", "final", "private"),
                            arrayOf("==", "===", "!=", "<>"),
                            arrayOf("Arrays", "Database access", "File upload", "Session control"),
                            arrayOf("__LINE__", "__FILE__", "__DIR__", "__PATH__"),
                            arrayOf("encrypt()", "md5()", "password_hash()", "hash_code()"),
                            arrayOf("multi", "use", "implements", "with"),
                            arrayOf("Warning", "Notice", "Parse / Fatal error", "User error"),
                            arrayOf("serialize()", "data_encode()", "stringify()", "pack()")
                        )
                        correctAnswer = arrayOf(1, 1, 2, 1, 1, 0, 2, 2, 2, 0)

                    }
                }
            }
            //..........................Swift Programming..............
            "Swift Programming"->{
                when(level){
                    "beginner" -> {
                        question = arrayOf(
                            "Swift was developed by?",
                            "Swift is mainly used for?",
                            "Which keyword is used to declare a variable?",
                            "Which keyword is used to declare a constant?",
                            "What is the file extension of Swift files?",
                            "Which function prints output to console?",
                            "Which operator is used for assignment?",
                            "Which collection stores ordered values?",
                            "Which symbol represents Optional in Swift?",
                            "Which IDE is used for Swift development?"
                        )
                        option = arrayOf(
                            arrayOf("Microsoft", "Google", "Apple", "IBM"),
                            arrayOf("Android apps", "iOS and macOS apps", "Windows apps", "Game consoles"),
                            arrayOf("static", "var", "let", "value"),
                            arrayOf("var", "let", "const", "static"),
                            arrayOf(".java", ".swift", ".kt", ".cpp"),
                            arrayOf("console()", "log()", "println()", "print()"),
                            arrayOf("==", "=", "=>", "<-"),
                            arrayOf("Tuple", "Array", "Dictionary", "Set"),
                            arrayOf("!", "?", "%", "&"),
                            arrayOf("Android Studio", "VS Code", "Xcode", "Eclipse")
                        )
                        correctAnswer = arrayOf(2, 1, 1, 1, 1, 3, 1, 1, 1, 2)
                    }

                    "medium" -> {
                        question = arrayOf(
                            "Which keyword is used to inherit a class?",
                            "Which keyword is used to conform to a protocol?",
                            "Which loop runs at least once?",
                            "Which keyword is used to handle errors?",
                            "Which collection stores key–value pairs?",
                            "What does ARC stand for?",
                            "Which keyword is used to create a function?",
                            "Which method is called when a class object is created?",
                            "Which keyword is used to exit from a loop?",
                            "Which operator is used for string interpolation?"
                        )
                        option = arrayOf(
                            arrayOf("inherits", "extends", "super", "class"),
                            arrayOf("follow", "implements", ":", "protocol"),
                            arrayOf("for", "while", "repeat-while", "foreach"),
                            arrayOf("handle", "catch", "try", "error"),
                            arrayOf("Array", "Dictionary", "Tuple", "Set"),
                            arrayOf("Automatic Risk Controller", "Automatic Reference Counting", "Application Runtime Code", "Association Reference Counter"),
                            arrayOf("fun", "method", "func", "function"),
                            arrayOf("start()", "init()", "run()", "setup()"),
                            arrayOf("stop()", "return", "exit()", "break"),
                            arrayOf("{}", "()", "\\()", "[]")
                        )
                        correctAnswer = arrayOf(3, 2, 2, 2, 1, 1, 2, 1, 3, 2)
                    }
                    "hard" -> {
                        question = arrayOf(
                            "Which programming paradigm does Swift support?",
                            "Which concept enables a class to have more than one initializer?",
                            "Which feature hides internal details of a class?",
                            "Which keyword prevents a class from being inherited?",
                            "Which property does not calculate a value until it's called?",
                            "Which keyword is used to define a closure?",
                            "Which feature allows storing a function as a variable?",
                            "Which keyword is used for memory cleanup during deinitialization?",
                            "What is used to release strong reference cycles in closures?",
                            "Which keyword creates a reference type?"
                        )
                        option = arrayOf(
                            arrayOf("Object-oriented", "Functional", "Both", "None"),
                            arrayOf("Polymorphism", "Overloading initializers", "Encapsulation", "Protocol conformance"),
                            arrayOf("Encapsulation", "Inheritance", "Polymorphism", "Abstraction"),
                            arrayOf("static", "final", "private", "sealed"),
                            arrayOf("Lazy property", "Static property", "Computed property", "Dynamic property"),
                            arrayOf("closure", "func", "in", "block"),
                            arrayOf("Protocol", "Tuple", "Function type", "Array"),
                            arrayOf("release()", "destroy()", "dispose()", "deinit"),
                            arrayOf("weak self", "optional self", "empty self", "static self"),
                            arrayOf("struct", "class", "enum", "protocol")
                        )
                        correctAnswer = arrayOf(2, 1, 0, 1, 0, 2, 2, 3, 0, 1)
                    }
                }
            }
            //..................Android Development............................
            "Android Development" -> {
                when (level) {
                    "beginner" -> {
                        question = arrayOf(
                            "Android is developed by?",
                            "Android apps are mainly written in?",
                            "Which language is official for Android development?",
                            "What is the build tool used in Android?",
                            "Which file stores all resources like strings and colors?",
                            "Which layout arranges views linearly?",
                            "Which view is used to display text?",
                            "Which component is used for user interaction?",
                            "Which folder stores XML layout files?",
                            "Which database is built into Android?"
                        )
                        option = arrayOf(
                            arrayOf("Apple", "Google", "Microsoft", "IBM"),
                            arrayOf("Swift", "Java and Kotlin", "Ruby", "C"),
                            arrayOf("C++", "Kotlin", "Python", "Swift"),
                            arrayOf("Gradle", "Maven", "Ant", "CMake"),
                            arrayOf("MainActivity", "strings.xml", "manifest.xml", "values.xml"),
                            arrayOf("LinearLayout", "ConstraintLayout", "FrameLayout", "ScrollView"),
                            arrayOf("ImageView", "Button", "TextView", "CardView"),
                            arrayOf("RecyclerView", "Button", "Menu", "Toast"),
                            arrayOf("drawable", "java", "layout", "assets"),
                            arrayOf("Room", "Firebase", "MongoDB", "MySQL")
                        )
                        correctAnswer = arrayOf(1, 1, 1, 0, 1, 0, 2, 1, 2, 0)
                    }
                    "medium" -> {
                        question = arrayOf(
                            "Which file contains app permissions?",
                            "Which method is called when an Activity starts?",
                            "Which layout is best for complex UI?",
                            "Which class is used to show popup message?",
                            "Which component is used for background tasks?",
                            "Which architecture pattern is recommended for Android apps?",
                            "Which component stores data permanently?",
                            "Which library is used for image loading?",
                            "Which component is used for list and grid?",
                            "What does APK stand for?"
                        )
                        option = arrayOf(
                            arrayOf("AndroidManifest.xml", "Gradle", "strings.xml", "layout.xml"),
                            arrayOf("onStop()", "onCreate()", "onPause()", "onDestroy()"),
                            arrayOf("LinearLayout", "ScrollView", "ConstraintLayout", "RelativeLayout"),
                            arrayOf("Intent", "Service", "Toast", "Adapter"),
                            arrayOf("Activity", "Fragment", "Service", "View"),
                            arrayOf("MVC", "MVVM", "MVM", "MVP"),
                            arrayOf("SharedPreferences", "Broadcast", "Intent", "Button"),
                            arrayOf("Picasso", "Volley", "Retrofit", "ButterKnife"),
                            arrayOf("ListAdapter", "RecyclerView", "ScrollView", "CardView"),
                            arrayOf("Android Package Kit", "Android Processing Kernel", "Application Package Key", "Android Position Keeper")
                        )
                        correctAnswer = arrayOf(0, 1, 2, 2, 2, 1, 0, 0, 1, 0)
                    }
                    "hard" -> {
                        question = arrayOf(
                            "Room database is built on top of?",
                            "Which Jetpack component is lifecycle aware?",
                            "Which concept prevents memory leaks in coroutines?",
                            "Which library is used for dependency injection?",
                            "Which component survives configuration changes?",
                            "Which keyword is used to launch coroutine?",
                            "Which paging library is used for loading large data smoothly?",
                            "Which annotation converts function into suspend?",
                            "Flow is a part of?",
                            "Which Gradle file controls app dependencies?"
                        )
                        option = arrayOf(
                            arrayOf("SQLite", "Firebase", "Realm", "MongoDB"),
                            arrayOf("Activity", "Service", "ViewModel", "Button"),
                            arrayOf("GlobalScope", "LifecycleScope", "CoroutineScope", "MainScope"),
                            arrayOf("Retrofit", "Picasso", "Hilt", "Gson"),
                            arrayOf("Activity", "Fragment", "ViewModel", "Adapter"),
                            arrayOf("go", "run", "launch", "start"),
                            arrayOf("WorkManager", "Paging 3", "DataStore", "ViewBinding"),
                            arrayOf("suspendable", "asyncable", "suspend", "delay"),
                            arrayOf("Kotlin Coroutines", "Volley", "Retrofit", "Dagger"),
                            arrayOf("settings.gradle", "app.gradle", "manifest.gradle", "module.gradle")
                        )
                        correctAnswer = arrayOf(0, 2, 2, 2, 2, 2, 1, 2, 0, 1)
                    }
                }
            }
            //................Flutter Development...................
            "Flutter Development"->{
                when(level){
                    "beginner" -> {
                        question = arrayOf(
                            "Flutter is developed by?",
                            "Flutter uses which programming language?",
                            "Which widget is used to display text?",
                            "Which function runs the Flutter app?",
                            "The root widget of a Flutter app is?",
                            "Which widget is used for constant UI that does not change?",
                            "Which command creates a Flutter project?",
                            "Which file stores Flutter app dependencies?",
                            "Which layout arranges widgets vertically?",
                            "Which folder stores UI code in Flutter project?"
                        )
                        option = arrayOf(
                            arrayOf("Apple", "Google", "Meta", "Microsoft"),
                            arrayOf("Swift", "Kotlin", "Dart", "Python"),
                            arrayOf("Label", "Text", "Title", "StringView"),
                            arrayOf("runMain()", "main()", "startApp()", "runFlutter()"),
                            arrayOf("MaterialApp", "MainApp", "AppRoot", "WidgetApp"),
                            arrayOf("StatefulWidget", "StatelessWidget", "DynamicWidget", "StaticWidget"),
                            arrayOf("flutter add", "flutter new project", "flutter create", "flutter start"),
                            arrayOf("build.yaml", "pubspec.yaml", "setup.yaml", "dependencies.txt"),
                            arrayOf("Row", "Column", "Stack", "Container"),
                            arrayOf("lib", "assets", "build", "flutter")
                        )
                        correctAnswer = arrayOf(1, 2, 1, 1, 0, 1, 2, 1, 1, 0)
                    }
                    "medium" -> {
                        question = arrayOf(
                            "Which widget can update UI dynamically?",
                            "Which keyword is used to declare a variable in Dart?",
                            "Which widget places widgets on top of each other?",
                            "Which function refreshes UI inside StatefulWidget?",
                            "Which package is used to make HTTP requests?",
                            "Which widget is used for scrolling lists?",
                            "Which concept improves app performance using only changed UI updates?",
                            "Which folder stores images and fonts?",
                            "Which widget provides app bar, body and floating action button?",
                            "Which command runs the Flutter app?"
                        )
                        option = arrayOf(
                            arrayOf("StatelessWidget", "StatefulWidget", "DynamicWidget", "BuilderWidget"),
                            arrayOf("var", "let", "value", "define"),
                            arrayOf("Column", "Row", "Stack", "Container"),
                            arrayOf("reload()", "refresh()", "setState()", "update()"),
                            arrayOf("http", "connect", "rest", "web"),
                            arrayOf("ListView", "TextView", "ScrollView", "RecyclerView"),
                            arrayOf("RenderOptimization", "PartialRendering", "WidgetRebuilding", "HotReloading"),
                            arrayOf("lib", "assets", "flutter", "resources"),
                            arrayOf("Scaffold", "AppFrame", "UIBuilder", "ScreenUI"),
                            arrayOf("flutter execute", "flutter run", "flutter go", "flutter start")
                        )
                        correctAnswer = arrayOf(1, 0, 2, 2, 0, 0, 2, 1, 0, 1)
                    }
                    "hard" -> {
                        question = arrayOf(
                            "Which Flutter architecture uses BLoC pattern?",
                            "Which Stream based state management uses events and states?",
                            "Which widget renders only visible items for large lists?",
                            "Which keyword is used to handle async functions?",
                            "Which API provides native device features like camera and sensors?",
                            "Which type is used for lazy loading lists?",
                            "Which Flutter engine is used for rendering UI?",
                            "Which state management uses ChangeNotifier?",
                            "Which function converts asynchronous data into widgets?",
                            "Which file contains Android settings inside Flutter project?"
                        )
                        option = arrayOf(
                            arrayOf("MVC", "MVVM", "BLoC Architecture", "MVP"),
                            arrayOf("Provider", "Riverpod", "Cubit", "Bloc"),
                            arrayOf("ListView.builder", "ScrollableList", "LazyList", "VirtualList"),
                            arrayOf("await", "late", "sync", "future"),
                            arrayOf("PlatformAPI", "ChannelAPI", "MethodChannel", "NativeConnect"),
                            arrayOf("ListView", "ListView.builder", "GridView", "GridView.extent"),
                            arrayOf("Flutter GPU", "Skia", "Vulkan", "OpenGL"),
                            arrayOf("Bloc", "Provider", "Redux", "GetX"),
                            arrayOf("FutureBuilder", "AsyncWidget", "LiveDataBuilder", "StreamWidget"),
                            arrayOf("pubspec.yaml", "settings.gradle", "androidManifest.xml", "main.dart")
                        )
                        correctAnswer = arrayOf(2, 3, 0, 0, 2, 1, 1, 1, 0, 1)
                    }
                }
            }
        }
    }

    private fun displayQuestion() {
        resetOptions()
        tvQuestion.text =  "Q${currentQuestionIndex + 1}. ${question[currentQuestionIndex]}"
        tvOption1.text = "a. ${option[currentQuestionIndex][0]}"
        tvOption2.text = "b. ${option[currentQuestionIndex][1]}"
        tvOption3.text = "c. ${option[currentQuestionIndex][2]}"
        tvOption4.text = "d. ${option[currentQuestionIndex][3]}"
        answerSelected = false
        nextBtn.isEnabled = false
        startTimer()

    }

    private fun checkAnswer(selectedIndex: Int) {
        if (answerSelected) return
        answerSelected = true

        val correctIndex = correctAnswer[currentQuestionIndex]
        val buttons = arrayOf(tvOption1, tvOption2, tvOption3, tvOption4)

        if (selectedIndex == correctIndex) {
            buttons[correctIndex].setBackgroundResource(R.drawable.correct_bg)
            score++
        } else {
            buttons[selectedIndex].setBackgroundResource(R.drawable.wrong_bg)
            buttons[correctIndex].setBackgroundResource(R.drawable.correct_bg)
        }

        nextBtn.isEnabled = true
    }

    private fun nextQuestion() {
        timer?.cancel()

        if (currentQuestionIndex < question.size - 1) {
            currentQuestionIndex++
            displayQuestion()
        } else {
            val intent = Intent(this, Result_Activity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("total", question.size)
            startActivity(intent)
            finish()
        }
    }




    private fun resetOptions() {
        tvOption1.setBackgroundResource(R.drawable.option_bg)
        tvOption2.setBackgroundResource(R.drawable.option_bg)
        tvOption3.setBackgroundResource(R.drawable.option_bg)
        tvOption4.setBackgroundResource(R.drawable.option_bg)
    }

    private fun startTimer() {
        timer?.cancel()

        progress.max = totalTime
        progress.progress = totalTime
        timer = object : CountDownTimer(30000, 1000) {  // 30 sec
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = (millisUntilFinished / 1000).toInt()
                progress.progress = secondsLeft
                timerText.text = "Time Left: $secondsLeft sec"
            }
            override fun onFinish() {
                Toast.makeText(this@MainActivity, "⏳ Time up!", Toast.LENGTH_SHORT).show()
                nextQuestion()
            }
        }.start()
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}
