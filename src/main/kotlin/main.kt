class Printer {
    fun printPages() {
        for (i in 1..5) {
            println("Printing page   ---   $i")
        }
    }
}

class SampleThread(private val threadName: String, var printer: Printer) : Thread() {
    private var thread: Thread = Thread(this, threadName)

    override fun run() {
        synchronized(printer) { printer.printPages() }
        println("Thread $threadName exiting.")
    }

    override fun start() {
        println("Starting $threadName")
        thread.start()
    }
}

object EntryPoint {
    @JvmStatic
    fun main(args: Array<String>) {
        val printDemo = Printer()
        val threadOne = SampleThread("Thread - 1 ", printDemo)
        val threadTwo = SampleThread("Thread - 2 ", printDemo)
        threadOne.start()
        threadTwo.start()
    }
}