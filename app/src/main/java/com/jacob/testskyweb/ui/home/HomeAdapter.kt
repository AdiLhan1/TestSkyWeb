package com.jacob.testskyweb.ui.home

import com.jacob.testskyweb.R
import com.jacob.testskyweb.base.BaseAdapter2
import com.jacob.testskyweb.databinding.CardviewItemBinding
import com.jacob.testskyweb.models.FilmModel
import com.jacob.testskyweb.utils.setImage

class HomeAdapter : BaseAdapter2<FilmModel, CardviewItemBinding>(
    R.layout.cardview_item, mutableListOf(), CardviewItemBinding::inflate
) {
    override fun onBind(binding: CardviewItemBinding, model: FilmModel) {
        binding.apply {
            infoText.text = model.author
            imageView.setImage(model.downloadUrl)
        }
    }
}