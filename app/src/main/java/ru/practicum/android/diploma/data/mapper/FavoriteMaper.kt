package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.data.db.entity.KeySkillsEntity
import ru.practicum.android.diploma.data.db.entity.PhonesEntity
import ru.practicum.android.diploma.data.db.entity.VacancyDataEntity
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel
import ru.practicum.android.diploma.domain.models.fields.AreaModel
import ru.practicum.android.diploma.domain.models.fields.ContactsModel
import ru.practicum.android.diploma.domain.models.fields.EmployerModel
import ru.practicum.android.diploma.domain.models.fields.EmploymentModel
import ru.practicum.android.diploma.domain.models.fields.ExperienceModel
import ru.practicum.android.diploma.domain.models.fields.KeySkillsModel
import ru.practicum.android.diploma.domain.models.fields.LogoUrlModel
import ru.practicum.android.diploma.domain.models.fields.PhonesModel
import ru.practicum.android.diploma.domain.models.fields.SalaryModel
import ru.practicum.android.diploma.domain.models.fields.ScheduleModel

fun VacancyDataEntity.mapToModel() = VacancyDetailsModel(
    id = vacancyDetails.id,
    area = AreaModel(
        id = null,
        name = vacancyDetails.nameArea,
        countryId = null,
        areas = null
    ),
    employer = vacancyDetails.employer?.mapToModel(),
    name = vacancyDetails.name,
    salary = SalaryModel(
        currency = vacancyDetails.currencySalary,
        from = vacancyDetails.fromSalary,
        gross = vacancyDetails.grossSalary,
        to = vacancyDetails.toSalary
    ),
    description = vacancyDetails.description,
    employment = EmploymentModel(
        id = null,
        name = vacancyDetails.nameEmployment
    ),
    experience = ExperienceModel(
        id = null,
        name = vacancyDetails.nameExperience
    ),
    contacts = ContactsModel(
        email = vacancyDetails.emailContact,
        name = vacancyDetails.nameContact,
        phones = phonesContact.mapToModel()
    ),
    schedule = ScheduleModel(
        id = null,
        name = vacancyDetails.nameSchedule
    ),
    keySkills = keySkills.mapToKeyModel(),
    alternateUrl = vacancyDetails.alternateUrl
)

fun List<KeySkillsEntity>?.mapToKeyModel() = this?.map { it.mapToModel() }

fun KeySkillsEntity.mapToModel() = KeySkillsModel(name)

fun List<PhonesEntity>?.mapToModel() = this?.map { it.mapToModel() }

fun PhonesEntity.mapToModel() = PhonesModel(
    cityCode, comment, countryCode, formatted, number
)

fun EmployerEntity.mapToModel() = EmployerModel(
    id = id,
    logoUrls = LogoUrlModel(original, logo90, logo240),
    name,
    trusted,
    url,
    vacanciesUrl
)
