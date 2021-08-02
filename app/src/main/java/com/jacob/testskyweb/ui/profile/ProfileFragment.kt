package com.jacob.testskyweb.ui.profile

import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.jacob.testskyweb.base.BaseFragment
import com.jacob.testskyweb.databinding.FragmentProfileBinding
import com.jacob.testskyweb.utils.gone
import com.jacob.testskyweb.utils.isValid
import com.jacob.testskyweb.utils.showSnackbar
import com.jacob.testskyweb.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun setClickListener() {
        binding.apply {
            btnSign.setOnClickListener {
                if (checkMyPswrd() && checkLogin()) {
                    progressBar.visible()
                    profileViewModel.getWeathers(498817)
                }
            }
        }
    }

    override fun setUpViewModel() {
        profileViewModel.state.observe(viewLifecycleOwner, { model ->
            model.let {
                showSnackbar(
                    "Город: ${it.name}, Температура: ${it.main.temp}, Облачность: ${it.clouds.all}, Влажность: ${it.main.humidity}",
                    Snackbar.LENGTH_LONG
                )
                binding.progressBar.gone()
            }
        })
        profileViewModel.isLoading.observe(viewLifecycleOwner, {
            if (!it) binding.progressBar.gone()
        })
    }

    private fun checkLogin(): Boolean {
        binding.run {
            val login = loginInput.editText?.text.toString().trim()
            if (login.isEmpty()) {
                loginInput.error = "Заполните поле!"
                return false
            } else {
                loginInput.error = null
                return true
            }
        }
    }

    private fun checkMyPswrd(): Boolean {
        binding.run {
            val password = passwordInput.editText?.text.toString().trim()
            if (password.length < 6) {
                passwordInput.error = "Пароль должен быть не меньше 6 символов"
                return false
            } else if (password.isEmpty()) {
                passwordInput.error = "Заполните поле!"
                return false
            } else {
                passwordInput.error = null
                if (passwordInput.isValid(password)) {
                    return true
                } else {
                    passwordInput.error =
                        "Пароль должен обязательно содержать минимум 1 строчную букву, 1 заглавную, и 1 цифру."
                    return false
                }
            }
        }
    }

}