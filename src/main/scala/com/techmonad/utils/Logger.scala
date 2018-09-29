package com.techmonad.utils

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
trait Logger {

  protected val logger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.getClass())

  protected def debug(message: String): Unit = logger.debug(message)

  protected def debug(message: String, exception: Throwable): Unit = logger.debug(message, exception)

  protected def info(message: String, exception: Throwable): Unit = logger.info(message, exception)

  protected def info(message: String): Unit = logger.info(message)

  protected def warn(message: String): Unit = logger.warn(message)

  protected def warn(message: String, exception: Throwable): Unit = logger.warn(message, exception)

  protected def error(message: String): Unit = logger.error(message)

  protected def error(message: String, exception: Throwable): Unit = logger.error(message, exception)

}
