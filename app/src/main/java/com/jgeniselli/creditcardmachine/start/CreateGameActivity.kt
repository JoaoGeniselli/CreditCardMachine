package com.jgeniselli.creditcardmachine.start

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.jgeniselli.creditcardmachine.R
import com.jgeniselli.creditcardmachine.match.MatchActivity
import com.jgeniselli.creditcardmachine.replaceFragment
import com.jgeniselli.creditcardmachine.showKeyboard
import kotlinx.android.synthetic.main.activity_create_game.*


class CreateGameActivity : AppCompatActivity() {

    private lateinit var viewModel: CreateGameViewModel
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)
        viewModel = ViewModelProviders.of(this).get(CreateGameViewModel::class.java)
        start_match_button.setOnClickListener { startMatch() }
        replaceFragment(NameListFragment(), R.id.frame)
    }

    private fun startMatch() {
        try {
            viewModel.validatePlayersForNewMatch()
            viewModel.createMatch()
            redirectToMatch()
        } catch (e: Exception) {
            showWarningMessage(R.string.cant_create_match_message)
        }
    }

    private fun redirectToMatch() {
        val intent = Intent(this, MatchActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_game, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.add_user -> onPlayerCreationSelected()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onPlayerCreationSelected() {
        when {
            viewModel.canCreateNewPlayer() -> showPlayerCreationDialog()
            else -> showWarningMessage(R.string.user_creation_unavailable_message)
        }
    }

    private fun showPlayerCreationDialog() {
        val editText = EditText(this)
        editText.addTextChangedListener(textWatcher())
        editText.setSingleLine(true)
        editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        val dialog = AlertDialog.Builder(this)
                .setTitle(R.string.username)
                .setView(editText)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create()

        dialog.setOnShowListener { dialogInterface ->
            tryToAddUser(dialogInterface as AlertDialog, editText)
        }
        dialog.show()
    }

    private fun textWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                username = text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }
    }

    private fun tryToAddUser(dialog: AlertDialog, editText: EditText) {
        val okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        okButton.setOnClickListener {
            when(editText.text.toString()) {
                "" -> editText.error = getString(R.string.player_name_error)
                else -> {
                    viewModel.addPlayer(editText.text.toString())
                    dialog.dismiss()
//                    hideKeyboard()
                }
            }
        }
        editText.requestFocus()
        showKeyboard()
    }

    private fun showWarningMessage(@StringRes message: Int) {
        AlertDialog.Builder(this)
                .setTitle(R.string.warning)
                .setMessage(message)
                .setNeutralButton(android.R.string.ok, null)
                .show()
    }
}
