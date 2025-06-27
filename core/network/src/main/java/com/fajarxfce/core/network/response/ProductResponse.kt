package com.fajarxfce.core.network.response

import com.fajarxfce.core.model.data.product.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataProduct? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class MediaItem(

	@field:SerializedName("manipulations")
	val manipulations: List<Any?>? = null,

	@field:SerializedName("order_column")
	val orderColumn: Int? = null,

	@field:SerializedName("file_name")
	val fileName: String? = null,

	@field:SerializedName("model_type")
	val modelType: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("model_id")
	val modelId: Int? = null,

	@field:SerializedName("custom_properties")
	val customProperties: List<Any?>? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("conversions_disk")
	val conversionsDisk: String? = null,

	@field:SerializedName("disk")
	val disk: String? = null,

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("generated_conversions")
	val generatedConversions: List<Any?>? = null,

	@field:SerializedName("responsive_images")
	val responsiveImages: List<Any?>? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("mime_type")
	val mimeType: String? = null,

	@field:SerializedName("original_url")
	val originalUrl: String? = null,

	@field:SerializedName("preview_url")
	val previewUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("collection_name")
	val collectionName: String? = null
)

data class Category(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataProduct(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("last_page")
	val lastPage: Int? = null,

	@field:SerializedName("next_page_url")
	val nextPageUrl: Any? = null,

	@field:SerializedName("prev_page_url")
	val prevPageUrl: Any? = null,

	@field:SerializedName("first_page_url")
	val firstPageUrl: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("last_page_url")
	val lastPageUrl: String? = null,

	@field:SerializedName("from")
	val from: Int? = null,

	@field:SerializedName("links")
	val links: List<LinksItem?>? = null,

	@field:SerializedName("to")
	val to: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class DataItem(

	@field:SerializedName("merk")
	val merk: Merk? = null,

	@field:SerializedName("sub_category")
	val subCategory: SubCategory? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("media")
	val media: List<MediaItem?>? = null,

	@field:SerializedName("product_category_id")
	val productCategoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("product_sub_category_id")
	val productSubCategoryId: Int? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("product_merk_id")
	val productMerkId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("sku")
	val sku: String? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("status")
	val status: Int? = null
)


data class Merk(

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

data class SubCategory(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("product_category_id")
	val productCategoryId: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class LinksItem(

	@field:SerializedName("active")
	val active: Boolean? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("url")
	val url: Any? = null
)


fun ProductResponse.toProduct(): List<Product> {
    return data?.data?.map { dataItem ->
        Product(
            id = dataItem?.id,
            name = dataItem?.name,
            price = dataItem?.price,
            merk = com.fajarxfce.core.model.data.product.Merk(
                id = dataItem?.merk?.id,
                name = dataItem?.merk?.name,
                description = dataItem?.merk?.description,
                createdAt = dataItem?.merk?.createdAt,
                updatedAt = dataItem?.merk?.updatedAt,
                status = dataItem?.merk?.status
            ),
            productMerkId = dataItem?.productMerkId,
            productCategoryId = dataItem?.productCategoryId,
            productSubCategoryId = dataItem?.productSubCategoryId,
            subCategory = com.fajarxfce.core.model.data.product.SubCategory(
                id = dataItem?.subCategory?.id,
                name = dataItem?.subCategory?.name,
                description = dataItem?.subCategory?.description,
                createdAt = dataItem?.subCategory?.createdAt,
                updatedAt = dataItem?.subCategory?.updatedAt,
                productCategoryId = dataItem?.subCategory?.productCategoryId,
                status = dataItem?.subCategory?.status
            ),
            category = com.fajarxfce.core.model.data.product.Category(
                id = dataItem?.category?.id,
                name = dataItem?.category?.name,
                description = dataItem?.category?.description,
                createdAt = dataItem?.category?.createdAt,
                updatedAt = dataItem?.category?.updatedAt,
                status = dataItem?.category?.status
            ),
            sku = dataItem?.sku,
            stock = dataItem?.stock,
            status = dataItem?.status,
            media = dataItem?.media?.map { mediaItem ->
                com.fajarxfce.core.model.data.product.MediaItem(
                    id = mediaItem?.id,
                    name = mediaItem?.name,
                    fileName = mediaItem?.fileName,
                    mimeType = mediaItem?.mimeType,
                    size = mediaItem?.size,
                    disk = mediaItem?.disk,
                    uuid = mediaItem?.uuid,
                    originalUrl = mediaItem?.originalUrl,
                    previewUrl = mediaItem?.previewUrl
                )
            } ?: emptyList()
            
        )
    } ?: emptyList()
}