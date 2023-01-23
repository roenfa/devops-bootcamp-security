# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# DEPLOY A COGNITO USER POOL
# This example deploys a Cognito User Pool with default settings that are
# defined in the variables.tf file of this module.
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

# ------------------------------------------------------------------------------
# PROVIDER CONFIGURATION
# ------------------------------------------------------------------------------

provider "aws" {
  region = "us-east-1"
}

# ------------------------------------------------------------------------------
# THE COGNITO USER POOL WITH DEFAULT SETTINGS
# ------------------------------------------------------------------------------

module "cognito_user_pool" {
  source  = "mineiros-io/cognito-user-pool/aws"
  version = "~> 0.9.0"

  name = "aclavijo-userpool"

  # We allow the public to create user profiles
  allow_admin_create_user_only = false

  advanced_security_mode           = "OFF"

  alias_attributes = [
    "email"
  ]
  auto_verified_attributes = [
    "email"
  ]

  account_recovery_mechanisms = [
    {
      name     = "verified_email"
      priority = 1
    }
  ]

  # If invited by an admin
  invite_email_subject = "You've been invited to AClavijo-Cognito"
  invite_email_message = "Hi {username}, your temporary password is '{####}'."
  invite_sms_message   = "Hi {username}, your temporary password is '{####}'."

  default_email_option  = "CONFIRM_WITH_LINK"
  email_subject_by_link = "Your Verification Link"
  email_message_by_link = "Please click the link below to verify your email address. {##Verify Email##}."
  sms_message           = "Your verification code is {####}."

  challenge_required_on_new_device = false
  user_device_tracking             = "OFF"

  # Require MFA
  mfa_configuration        = "OFF"
  allow_software_mfa_token = false

  password_minimum_length    = 8
  password_require_lowercase = true
  password_require_numbers   = true
  password_require_uppercase = true
  password_require_symbols   = true

  temporary_password_validity_days = 7

  schema_attributes = [
    {
      name                     = "role"
      type                     = "String"
      developer_only_attribute = false,
      mutable                  = true,
      required                 = false,
      min_length               = 0,
      max_length               = 2048
    }
  ]

  clients = [
    {
      name                 = "aclavijo-UI-client",
      access_token_validity = 60,
      explicit_auth_flows = ["ALLOW_ADMIN_USER_PASSWORD_AUTH","ALLOW_REFRESH_TOKEN_AUTH"],
      id_token_validity = 60,
      token_validity_units = {
        access_token = "minutes"
        id_token      = "minutes"
        refresh_token = "days"
      }
    }
  ]

  tags = {
    environment = "aclavijo"
  }
}

resource "aws_cognito_user_group" "Trainer" {
  name         = "Trainer"
  user_pool_id = module.cognito_user_pool.user_pool.id
  description  = "Group of Trainers"
  precedence   = 1
  role_arn     = ""
}
resource "aws_cognito_user_group" "Student" {
  name         = "Student"
  user_pool_id = module.cognito_user_pool.user_pool.id
  description  = "Group of Students"
  precedence   = 2
  role_arn     = ""
}

# resource "aws_dynamodb_table" "students" {
#   name           = "students"
#   billing_mode   = "PAY_PER_REQUEST"
#   hash_key       = "id"
#   attribute {
#     name = "id"
#     type = "S"
#   }
#   attribute {
#     name = "name"
#     type = "S"
#   }
#   attribute {
#     name = "lastname"
#     type = "S"
#   }
#   attribute {
#     name = "email"
#     type = "S"
#   }
#   attribute {
#     name = "country"
#     type = "S"
#   }
# }

# resource "aws_dynamodb_table" "subjects" {
#   name           = "subjects"
#   billing_mode   = "PAY_PER_REQUEST"
#   hash_key       = "id"
#   attribute {
#     name = "id"
#     type = "S"
#   }
#   attribute {
#     name = "student_id"
#     type = "S"
#   }
#   attribute {
#     name = "trainer_id"
#     type = "S"
#   }
#   attribute {
#     name = "name"
#     type = "S"
#   }
#   attribute {
#     name = "grade"
#     type = "S"
#   }
# }