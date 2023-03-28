package com.iiddd.abnamrorepos.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.iiddd.abnamrorepos.databinding.FragmentRepoDetailsBinding
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.utils.StringUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRepoDetailsBinding

    private val viewModel: RepoDetailsViewModel by viewModels()
    private val args: RepoDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setRepoId(args.repoId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.repo.collectLatest {
                setRepo(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
    }

    private fun setRepo(repo: Repo) {
        with(binding) {
            nameTextView.text = repo.name
            fullNameValueTextView.text = repo.fullName
            visibilityValueTextView.text = StringUtils.capitalize(repo.visibility)
            isPrivateValueTextView.text = StringUtils.getIsPrivateFriendlyString(repo.isPrivate)
            descriptionValueTextView.text = repo.description
            goToRepoButton.setOnClickListener {
                navigateToExternalUrl(repo.htmlUrl)
            }
            repo.imageUrl?.let {
                Glide
                    .with(binding.ownersAvatarImageView)
                    .load(repo.imageUrl)
                    .into(binding.ownersAvatarImageView)
            }
        }
    }

    private fun navigateToExternalUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context?.startActivity(intent)
    }
}