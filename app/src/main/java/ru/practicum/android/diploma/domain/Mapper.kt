package ru.practicum.android.diploma.domain

import ru.practicum.android.diploma.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.data.db.entity.KeySkillsEntity
import ru.practicum.android.diploma.data.db.entity.PhonesEntity
import ru.practicum.android.diploma.data.db.entity.VacancyDataEntity
import ru.practicum.android.diploma.data.db.entity.VacancyDetailsEntity
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel
import ru.practicum.android.diploma.domain.models.fields.EmployerModel
import ru.practicum.android.diploma.domain.models.fields.KeySkillsModel
import ru.practicum.android.diploma.domain.models.fields.PhonesModel
import java.util.UUID

fun VacancyDetailsModel.mapToEntity() = VacancyDataEntity(
    VacancyDetailsEntity(
        id = id,
        nameArea = area?.name,
        employer = employer?.mapToEntity(),
        name = name,
        currencySalary = salary?.currency,
        fromSalary = salary?.from,
        grossSalary = salary?.gross,
        toSalary = salary?.to,
        description = description,
        nameEmployment = employment?.name,
        nameExperience = experience?.name,
        emailContact = contacts?.email,
        nameContact = contacts?.name,
        nameSchedule = schedule?.name,
        alternateUrl = alternateUrl
    ),
    keySkills = keySkills.mapToKeyEntity(id),
    phonesContact = contacts?.phones?.mapToEntity(id)
)

fun List<KeySkillsModel>?.mapToKeyEntity(vacancyId: String) = this?.map { it.mapToEntity(vacancyId) }

fun KeySkillsModel.mapToEntity(vacancyId: String) = KeySkillsEntity(
    id = UUID.randomUUID().toString(),
    vacancyId = vacancyId,
    name = name
)

fun List<PhonesModel>?.mapToEntity(vacancyId: String) = this?.map { it.mapToEntity(vacancyId) }

fun PhonesModel.mapToEntity(vacancyId: String) = PhonesEntity(
    id = UUID.randomUUID().toString(),
    number = number,
    vacancyId = vacancyId,
    cityCode = cityCode,
    comment = comment,
    countryCode = countryCode,
    formatted = formatted,
)

fun EmployerModel.mapToEntity() = EmployerEntity(
    id,
    name,
    trusted,
    url,
    vacanciesUrl,
    logoUrls?.original,
    logoUrls?.logo90,
    logoUrls?.logo240
)
