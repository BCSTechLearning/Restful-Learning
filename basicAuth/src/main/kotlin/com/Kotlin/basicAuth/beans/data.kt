package com.Kotlin.basicAuth.beans

import io.swagger.annotations.ApiModelProperty

open class data(@ApiModelProperty(notes = "Provided User name", required = true) var user: String?,
                @ApiModelProperty(notes = "Provided Password", required = true) var message: String?)