module.exports = function(grunt) {

    var jsLibraries = [
        'src/main/webapp/components/jquery/dist/jquery.js',
        'src/main/webapp/components/bootstrap/dist/js/bootstrap.js',
        'src/main/webapp/components/angular/angular.js',
        'src/main/webapp/components/angular-resource/angular-resource.js',
        'src/main/webapp/components/angular-route/angular-route.js',
        'src/main/webapp/generated/spring-security-csrf-token-interceptor.js',
        'src/main/webapp/js/constants.js'
    ];

    grunt.initConfig({
        uglify: {
            sputnik: {
                files: {
                    'src/main/webapp/resources/js/sputnik.min.js': jsLibraries.concat([
                        'src/main/webapp/js/base.js',
                        'src/main/webapp/js/**/*.js',
                        '!src/main/webapp/js/admin.js',
                        '!src/main/webapp/js/admin/*'

                    ])
                }
            },
            admin: {
                files: {
                    'src/main/webapp/resources/js/admin.min.js': jsLibraries.concat([
                        'src/main/webapp/js/admin.js',
                        'src/main/webapp/js/navbar/navbarDirective.js',
                        'src/main/webapp/js/navbar/navbarController.js',
                        'src/main/webapp/js/profile/profileRepository.js',
                        'src/main/webapp/js/admin/*'
                    ])
                }
            },
            options: {
                sourceMap: true,
                sourceMapIncludeSources: true
            }
        },
        cssmin: {
            combine: {
                files: {
                    'src/main/webapp/resources/css/sputnik.css': [
                        'src/main/webapp/components/bootstrap/dist/css/bootstrap.css',
                        'src/main/webapp/css/base.css'
                    ]
                }
            }
        },
        watch: {
            scripts: {
                files: ['src/main/webapp/**/*.js'],
                tasks: ['js'],
                options: {
                    spawn: false
                }
            },
            style: {
                files: ['src/main/webapp/**/*.css'],
                tasks: ['css'],
                options: {
                    spawn: false
                }
            }
        },
        notify: {
            completed: {
                options: {
                    title: 'Build completed',
                    message: 'Resources are up to date.'
                }
            }
        },
        ngconstant: {
            dist: {
                options: {
                    dest: 'src/main/webapp/js/constants.js',
                    name: 'constants'
                },
                constants: {
                    mapsApiKey: process.env.MAPS_API_KEY
                }
            }
        },
        ngAnnotate: {
            options: {
                singleQuotes: true
            },
            'csrf-interceptor': {
                files: {
                    'src/main/webapp/generated/spring-security-csrf-token-interceptor.js': ['src/main/webapp/components/spring-security-csrf-token-interceptor/src/spring-security-csrf-token-interceptor.js']
                }
            }
        }
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-notify');
    grunt.loadNpmTasks('grunt-ng-constant');
    grunt.loadNpmTasks('grunt-ng-annotate');

    grunt.registerTask('js', ['ngAnnotate:csrf-interceptor', 'ngconstant', 'uglify:sputnik', 'uglify:admin', 'notify:completed']);
    grunt.registerTask('css', ['cssmin', 'notify:completed']);

    // Default task(s).
    grunt.registerTask('default', ['ngAnnotate:csrf-interceptor', 'ngconstant', 'uglify:sputnik', 'uglify:admin', 'cssmin', 'notify:completed']);
};