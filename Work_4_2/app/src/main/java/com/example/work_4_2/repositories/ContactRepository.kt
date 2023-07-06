package com.example.work_4_2.repositories

import com.example.work_4_2.data.Contact

object ContactRepository {
    private val contacts = mutableListOf(
        Contact(
            1,
            "lastName",
            "firstName 1",
            "354163416",
            "https://freepngimg.com/thumb/youtube/63841-profile-twitch-youtube-avatar-discord-free-download-image.png",
        ),
        Contact(
            2,
            "lastName",
            "firstName 2",
            "234632462",
            "https://imagedelivery.net/9sCnq8t6WEGNay0RAQNdvQ/UUID-cl9h19yx60869qdopuprd1w37/public",
        ),
        Contact(
            3,
            "lastName",
            "firstName 3",
            "6474323",
            "https://i.etsystatic.com/33714944/r/il/a4abb5/3699607662/il_1080xN.3699607662_h8ax.jpg",
        ),
        Contact(
            4,
            "lastName",
            "firstName 4",
            "565696976855",
            "https://wide-w.com/wp-content/uploads/2023/01/4-720x720.png",
        ),
    )

    fun getContacts(): List<Contact> {
        return contacts.map { it.copy() }
    }

    fun updateContact(contact: Contact) {
        val oldContact = contacts.first { it.id == contact.id }
        val index = contacts.indexOf(oldContact)
        contacts[index] = contact
    }
}