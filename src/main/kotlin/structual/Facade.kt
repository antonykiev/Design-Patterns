package structual


fun main(args: Array<String>) {
    val computer = Computer()
    computer.start()
}

class CPU {
    fun freeze() = println("Freezing.")

    fun jump(position: Long) = println("Jump to $position.")

    fun execute() = println("Executing.")
}

class HardDrive {
    fun read(lba: Long, size: Int): ByteArray = byteArrayOf()
}

class Memory {
    fun load(position: Long, data: ByteArray) = println("Loading from memory position: $position")
}

/* Facade */
class Computer(
    private val processor: CPU = CPU(),
    private val ram: Memory = Memory(),
    private val hd: HardDrive = HardDrive()
) {
    companion object {
        const val BOOT_ADDRESS = 0L
        const val BOOT_SECTOR = 0L
        const val SECTOR_SIZE = 0
    }

    fun start() {
        processor.freeze()
        ram.load(BOOT_ADDRESS, hd.read(BOOT_SECTOR, SECTOR_SIZE))
        processor.jump(BOOT_ADDRESS)
        processor.execute()
    }
}