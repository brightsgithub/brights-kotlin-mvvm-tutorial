package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.states

import cleanarcpro.brightowusu.com.cleanarcproj.models.UIAboutUser

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-31.
 *
 * When checking for equality when testing sealed classes, we need to override
 * hashCode and equals: https://medium.com/@oziemowa/android-viewmodel-testing-with-view-states-2a30c461939b
 *
 * I have simply used the IDE to generate these two for each class below
 */
sealed class DisplayUserViewState {

    class Success(val user: UIAboutUser) : DisplayUserViewState() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Success

            if (user != other.user) return false

            return true
        }

        override fun hashCode(): Int {
            return user.hashCode()
        }
    }

    class UserExists: DisplayUserViewState() {
        override fun hashCode(): Int = javaClass.hashCode()
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

    }

    class UserDoesNotExist: DisplayUserViewState() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    class Error(val exception: Throwable) : DisplayUserViewState() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Error

            if (exception != other.exception) return false

            return true
        }

        override fun hashCode(): Int {
            return exception.hashCode()
        }
    }

    class Loading : DisplayUserViewState() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }
}