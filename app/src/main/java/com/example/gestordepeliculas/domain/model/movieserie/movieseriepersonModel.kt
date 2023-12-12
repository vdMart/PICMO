package com.example.gestordepeliculas.domain.model.movieserie

import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems

interface movieseriepersonModel {
    val typeModel: TabItems
    val id: Int
    val title_name: String?
    val photo_path: String?
}